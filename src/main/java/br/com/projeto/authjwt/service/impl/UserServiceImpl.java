package br.com.projeto.authjwt.service.impl;


import br.com.projeto.authjwt.dto.UserDto;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.BusinessException;
import br.com.projeto.authjwt.models.exceptions.ConflictException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.models.mapper.UserMapper;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.service.email.EmailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        log.debug("POST registerUser userDto received {} ", userDto.toString());
        if (existsByUsername(userDto.getUsername())) {
            log.warn("Username {} is Already Taken ", userDto.getUsername());
            throw new ConflictException(
                    String.format("Error: Username is Already Taken! %s ", userDto.getUsername()));
        }
        if (existsByEmail(userDto.getEmail())) {
            log.warn("Email {} is Already Taken ", userDto.getEmail());
            throw new ConflictException(
                    String.format("\"Error: Email is Already Taken! %s ", userDto.getEmail()));
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleService.findByRoleName(RoleType.ROLE_USER);

        User user = userMapper.toEntity(userDto);
        user.getRoles().add(role);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userMapper.toModel(user);

    }

    public User buscarOuFalharPorEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Não existe um cadastro de usuário com email %s", email)));
    }

    @Override
    public UserDto resetPassword(String email) {

        User user = buscarOuFalharPorEmail(email);
        //Nova Senha
        String password = UUID.randomUUID().toString();
        user.setPassword(encodePassword(password));
        user = userRepository.save(user);
        log.info("New user password: " + password);

        emailService.sendNewPasswordEmail(user);
        log.info("Mail send successfully new password: " + password);

        return userMapper.toModel(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
