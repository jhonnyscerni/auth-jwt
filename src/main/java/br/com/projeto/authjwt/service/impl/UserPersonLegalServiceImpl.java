package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.UserPersonLegalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonLegalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserPersonLegalService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.utils.LogicVerifyPersonTypeLogin;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class UserPersonLegalServiceImpl implements UserPersonLegalService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final RoleService roleService;

    private final UserPersonLegalMapper userPersonLegalMapper;

    private final PasswordEncoder passwordEncoder;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;


    @Override
    @Transactional
    public UserResponse findByPersonLegalIdUserUserPersonLegalResponse(UUID personId) {
        Optional<User> userOptional = userRepository.findByPersonIdUserDto(personId);

        if (userOptional.isPresent()) {
            return userPersonLegalMapper.toResponse(userOptional.get());
        }
        log.debug("GET UUID personId received {} ", userOptional);
        return userPersonLegalMapper.toResponse(new User());
    }

    @Override
    public UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest) {
        return userService.createPersonUser(personId, userAddPersonRequest, PersonType.LEGAL);
    }

    @Override
    public UserResponse create(UserPersonLegalRequest userPersonLegalRequest) {
        log.debug("POST UserPersonLegalRequest userPersonLegalRequest received {} ", userPersonLegalRequest.toString());

        userService.existsByUserName(new User(), userPersonLegalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(userPersonLegalRequest);

        userPersonLegalRequest.setPassword(passwordEncoder.encode(userPersonLegalRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role dashboard = roleService.findByRoleName(RoleType.ROLE_DASHBOARD.name());
        Role pessoas = roleService.findByRoleName(RoleType.ROLE_PESSOAS.name());
        Role empresas = roleService.findByRoleName(RoleType.ROLE_EMPRESAS.name());
        userPersonLegalRequest.getRoles().add(dashboard);
        userPersonLegalRequest.getRoles().add(pessoas);
        userPersonLegalRequest.getRoles().add(empresas);

        User user = userPersonLegalMapper.create(userPersonLegalRequest);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userPersonLegalMapper.toResponse(user);

    }

    @Override
    public UserResponse update(UUID id, UserPersonLegalRequest userPersonLegalRequest) {
        log.debug("PUT UUID idPerson Legal {} ", id.toString());
        log.debug("PUT UserPersonLegalRequest userPersonLegalRequest received {} ", userPersonLegalRequest.toString());
        User user = userService.buscarOuFalhar(id);

        userService.existsByUserName(user, userPersonLegalRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userPersonLegalRequest);

        userPersonLegalMapper.update(user, userPersonLegalRequest);

        User save = userRepository.save(user);
        log.debug("PUT update userId saved {} ", user.getId());
        log.info("User update successfully userId {} ", user.getId());
        return userPersonLegalMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void passwordNotEquals(User user, UserPersonLegalRequest userPersonLegalRequest) {
        log.debug("Verify password {} ", user.getId());
        if (!user.getPassword().equals(userPersonLegalRequest.getPassword())) {
            userPersonLegalRequest.setPassword(passwordEncoder.encode(userPersonLegalRequest.getPassword()));
        }
    }

}
