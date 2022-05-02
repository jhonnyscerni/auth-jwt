package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.UserMapper;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.repositories.specs.UserSpecification;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.service.email.EmailService;
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

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private static final String MSG_OBJECT_IN_USE
        = "code user %d cannot be removed as it is in use";

    @Override
    @Transactional
    public User buscarOuFalhar(Long usuarioId) {
        return userRepository.findById(usuarioId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de usuário", usuarioId));
    }

    @Override
    @Transactional
    public UserResponse findById(Long id) {
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
    public Page<UserResponse> search(UserFilter filter, Pageable pageable) {
        log.debug("GET UserFilter filter received {} ", filter.toString());
        return userRepository.findAll(new UserSpecification(filter), pageable).map(userMapper::toResponse);
    }

    @Override
    @Transactional
    public void disassociateRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.removeRole(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void connectRole(Long userId, Long roleId) {
        User user = buscarOuFalhar(userId);
        Role role = roleService.buscarOuFalhar(roleId);
        user.addRole(role);
        userRepository.save(user);
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
}
