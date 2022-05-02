package br.com.projeto.authjwt.service.impl;


import br.com.projeto.authjwt.api.mapper.UserMapper;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.ConflictException;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.repositories.specs.UserSpecification;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.service.email.EmailService;

import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private final PersonPhysicalService personPhysicalService;

    private static final String MSG_OBJECT_IN_USE
            = "code user %d cannot be removed as it is in use";

    @Override
    @Transactional
    public User buscarOuFalharPorEmail(String email) {
        log.debug("GET String email received {} ", email);
        return userRepository.findByPersonEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("There is no user registration with email %s", email)));
    }

    @Override
    @Transactional
    public User buscarOuFalhar(Long usuarioId) {
        log.debug("GET Long usuarioId received {} ", usuarioId.toString());
        return userRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("There is no user registration", usuarioId));
    }

    @Override
    @Transactional
    public UserResponse findByIdUserDto(Long id) {
        User user = buscarOuFalhar(id);
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public Page<UserResponse> search(UserFilter filter, Pageable pageable) {
        log.debug("GET UserFilter filter received {} ", filter.toString());
        return userRepository.findAll(new UserSpecification(filter), pageable).map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public UserResponse saveUser(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());

        existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role dashboard = roleService.findByRoleName(RoleType.ROLE_DASHBOARD.name());
        Role pessoas = roleService.findByRoleName(RoleType.ROLE_PESSOAS.name());
        Role empresas = roleService.findByRoleName(RoleType.ROLE_EMPRESAS.name());
        userPersonPhysicalRequest.getRoles().add(dashboard);
        userPersonPhysicalRequest.getRoles().add(pessoas);
        userPersonPhysicalRequest.getRoles().add(empresas);

        User user = userMapper.create(userPersonPhysicalRequest);
        user = userRepository.save(user);
        log.debug("POST saveUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userMapper.toResponse(user);

    }

    @Override
    @Transactional
    public UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());

        existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());
        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));

        User user = userMapper.create(userPersonPhysicalRequest);
        userRepository.save(user);
        log.debug("POST create userId saved {} ", user.getId());
        log.info("User create successfully userId {} ", user.getId());
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("PUT Long id received {} ", id.toString());
        log.debug("PUT UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());
        User user = buscarOuFalhar(id);

        existsByUserName(user, userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userPersonPhysicalRequest);

        userMapper.update(user, userPersonPhysicalRequest);

        User save = userRepository.save(user);
        log.debug("PUT update userId saved {} ", user.getId());
        log.info("User update successfully userId {} ", user.getId());
        return userMapper.toResponse(save);

    }

    @Override
    @Transactional
    public void connectRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.addRole(role);
        log.debug("Add in Role roleId {} ", roleId);
        userRepository.save(user);
        log.info("User connectRole successfully roleId {} ",roleId);
    }

    @Override
    @Transactional
    public UserResponse findByPersonIdUserDto(Long personId) {
        Optional<User> userOptional = userRepository.findByPersonIdUserDto(personId);

        if (userOptional.isPresent()) {
            return userMapper.toResponse(userOptional.get());
        }
        log.debug("GET Long personId received {} ", userOptional);
        return userMapper.toResponse(new User());
    }

    @Override
    @Transactional
    public UserResponse createPersonUser(Long personId, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST Long personId received {} ", personId.toString());
        log.debug("POST UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());

        PersonPhysical personPhysical = personPhysicalService.buscarOuFalhar(personId);
        existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());
        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        userPersonPhysicalRequest.setPerson(personPhysical);

        User user = userMapper.create(userPersonPhysicalRequest);
        userRepository.save(user);
        log.debug("POST create userId saved {} ", user.getId());
        log.info("User create successfully userId {} ", user.getId());
        return userMapper.toResponse(user);
    }

    @Override
    @Transactional
    public void disassociateRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.removeRole(role);
        log.debug("removed in Role roleId {} ", roleId);
        userRepository.save(user);
        log.info("User disassociateRole successfully userId {} ", user.getId());
    }

    @Override
    @Transactional
    public void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("Verify password {} ", user.getId());
        if (!user.getPassword().equals(userPersonPhysicalRequest.getPassword())) {
            userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("User {} not found", id);
            throw new EntityNotFoundException("User not found");

        } catch (DataIntegrityViolationException e) {
            log.warn("User {} cannot be removed as it is in use", id);
            throw new EntityInUseException(
                    String.format(MSG_OBJECT_IN_USE, id));
        }
    }

    @Override
    @Transactional
    public UserResponse resetPassword(String email) {

        User user = buscarOuFalharPorEmail(email);
        //Nova Senha
        String password = UUID.randomUUID().toString();
        user.setPassword(encodePassword(password));
        user = userRepository.save(user);
        log.info("New user password: " + password);

        emailService.sendNewPasswordEmail(user);
        log.info("Mail send successfully new password: " + password);

        return userMapper.toResponse(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    @Transactional
    public void existsByUserName(User cliente, String username) {
        Optional<User> clienteExistente = userRepository.findByUsername(username);

        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            log.warn("Username {} is Already Taken ", clienteExistente.get().getUsername());
            throw new ConflictException(
                    String.format("Error: Username is Already Taken! %s ", clienteExistente.get().getUsername()));
        }
    }

    // @Override
    // public void existsByUserEmail(User cliente, String email) {
    //     Optional<User> clienteExistente = userRepository.findByEmail(email);
//
//        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
//            log.warn("Email {} is Already Taken ", clienteExistente.get().getEmail());
//            throw new ConflictException(
//                String.format("\"Error: Email is Already Taken! %s ", clienteExistente.get().getEmail()));
//        }
//    }

}
