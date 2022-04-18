package br.com.projeto.authjwt.service.impl;


import br.com.projeto.authjwt.api.mapper.UserMapper;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.ConflictException;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.repositories.specs.UserSpecification;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.service.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    private static final String MSG_PERMISSAO_EM_USO
        = "Usuário de código %d não pode ser removida, pois está em uso";

    @Override
    public User buscarOuFalharPorEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("Não existe um cadastro de usuário com email %s", email)));
    }

    @Override
    public User buscarOuFalhar(Long usuarioId) {
        return userRepository.findById(usuarioId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de usuário", usuarioId));
    }

    public UserResponse findByIdUserDto(Long id) {
        User user = buscarOuFalhar(id);
        return userMapper.toResponse(user);
    }

    @Override
    public Page<UserResponse> search(UserFilter filter, Pageable pageable) {
        return userRepository.findAll(new UserSpecification(filter), pageable).map(userMapper::toResponse);
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        log.debug("POST registerUser userDto received {} ", userRequest.toString());

        existsByUserName(new User(), userRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role role = roleService.findByRoleName(RoleType.ROLE_USER);
        userRequest.getRoles().add(role);

        User user = userMapper.create(userRequest);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse create(UserRequest userRequest) {

        existsByUserName(new User(), userRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        User cliente = userMapper.create(userRequest);
        userRepository.save(cliente);
        return userMapper.toResponse(cliente);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        User user = buscarOuFalhar(id);

        existsByUserName(user, userRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userRequest);

        userMapper.update(user, userRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public void connectRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void disassociateRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.removeRole(role);
        userRepository.save(user);
    }

    @Override
    public void passwordNotEquals(User user, UserRequest userRequest) {
        if (!user.getPassword().equals(userRequest.getPassword())){
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Usuario não encontrado");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }

    @Override
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
