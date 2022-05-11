package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.UserMapper;
import br.com.projeto.authjwt.api.mapper.UserPersonLegalMapper;
import br.com.projeto.authjwt.api.mapper.UserPersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserPersonLegalResponse;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.models.exceptions.ConflictException;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.PersonLegalService;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.service.email.EmailService;
import br.com.projeto.authjwt.utils.LogicVerifyPersonTypeLogin;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    private final PersonLegalService personLegalService;

    private final PersonPhysicalService personPhysicalService;

    private final UserMapper userMapper;

    private final UserPersonLegalMapper userPersonLegalMapper;

    private final UserPersonPhysicalMapper userPersonPhysicalMapper;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;

    private static final String MSG_OBJECT_IN_USE
        = "code user %d cannot be removed as it is in use";

    @Override
    @Transactional
    public User buscarOuFalhar(UUID usuarioId) {
        return userRepository.findById(usuarioId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de usuário", usuarioId));
    }

    @Override
    @Transactional
    public UserResponse findById(UUID id) {
        User user = buscarOuFalhar(id);
        return userMapper.toResponse(user);
    }

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
    public void disassociateRole(UUID userId, UUID roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.removeRole(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void connectRole(UUID userId, UUID roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
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

    @Override
    public UserResponse update(UUID userId, UserRequest userRequest) {
        log.debug("PUT UUID userId received {} ", userId.toString());
        log.debug("PUT UserRequest userRequest received {} ", userRequest.toString());
        User user = buscarOuFalhar(userId);

        existsByUserName(user, userRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userRequest);

        userMapper.update(user, userRequest);

        User save = userRepository.save(user);
        log.debug("PUT update userId saved {} ", user.getId());
        log.info("User update successfully userId {} ", user.getId());
        return userMapper.toResponse(save);
    }

    @Override
    public UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest, PersonType personType) {
        log.debug("POST UUID personId received {} ", personId.toString());
        log.debug("POST UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userAddPersonRequest.toString());
        existsByUserName(new User(), userAddPersonRequest.getUsername());
        userAddPersonRequest.setPassword(passwordEncoder.encode(userAddPersonRequest.getPassword()));
        if (Objects.equals(personType, PersonType.LEGAL)) {
            log.debug("String tipoPerson {} ", personType.name());
            PersonLegal personLegal = personLegalService.buscarOuFalhar(personId);

            User user = userPersonLegalMapper.add(userAddPersonRequest);
            user.setPerson(personLegal);
            userRepository.save(user);
            log.debug("POST create userId saved {} ", user.getId());
            log.info("User create successfully userId {} ", user.getId());
            return userPersonLegalMapper.toResponse(user);
        }

        PersonPhysical personPhysical = personPhysicalService.buscarOuFalhar(personId);

        User user = userPersonPhysicalMapper.add(userAddPersonRequest);
        user.setPerson(personPhysical);
        userRepository.save(user);
        log.debug("POST create userId saved {} ", user.getId());
        log.info("User create successfully userId {} ", user.getId());
        return userPersonPhysicalMapper.toResponse(user);

    }

    @Override
    public UserPersonPhysicalResponse findByIdPersonPhysical(UUID userId) {
        User user = userRepository.findByIdPersonPhysical(userId)
            .orElseThrow(() -> new EntityNotFoundException("There is no record of userId Person Physical", userId));
        return userPersonPhysicalMapper.toResponseUserPersonPhysical(user);
    }

    @Override
    public UserPersonLegalResponse findByIdPersonLegal(UUID userId) {
        User user = userRepository.findByIdPersonLegal(userId)
            .orElseThrow(() -> new EntityNotFoundException("There is no record of userId Person Legal", userId));
        return userPersonLegalMapper.toResponseUserLegalPhysical(user);
    }

    @Override
    public void passwordNotEquals(User user, UserRequest userRequest) {
        log.debug("Verify password {} ", user.getId());
        if (!user.getPassword().equals(userRequest.getPassword())) {
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
    }

    @Override
    public void existsByUserName(User cliente, String username) {
        Optional<User> clienteExistente = userRepository.findByUsername(username);

        if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
            log.warn("Username {} is Already Taken ", clienteExistente.get().getUsername());
            throw new ConflictException(
                String.format("Error: Username is Already Taken! %s ", clienteExistente.get().getUsername()));
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
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
