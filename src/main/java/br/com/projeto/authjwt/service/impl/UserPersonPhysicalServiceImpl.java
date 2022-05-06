package br.com.projeto.authjwt.service.impl;


import br.com.projeto.authjwt.api.mapper.UserPersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserPersonPhysicalService;
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
public class UserPersonPhysicalServiceImpl implements UserPersonPhysicalService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final UserPersonPhysicalMapper userPersonPhysicalMapper;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;


    @Override
    @Transactional
    public UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST registerUser userDto received {} ", userPersonPhysicalRequest.toString());

        userService.existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(userPersonPhysicalRequest);

        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role dashboard = roleService.findByRoleName(RoleType.ROLE_DASHBOARD.name());
        Role pessoas = roleService.findByRoleName(RoleType.ROLE_PESSOAS.name());
        Role empresas = roleService.findByRoleName(RoleType.ROLE_EMPRESAS.name());
        userPersonPhysicalRequest.getRoles().add(dashboard);
        userPersonPhysicalRequest.getRoles().add(pessoas);
        userPersonPhysicalRequest.getRoles().add(empresas);

        User user = userPersonPhysicalMapper.create(userPersonPhysicalRequest);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userPersonPhysicalMapper.toResponse(user);

    }

    @Override
    @Transactional
    public UserResponse update(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("PUT UUID id received {} ", id.toString());
        log.debug("PUT UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());
        User user = userService.buscarOuFalhar(id);

        userService.existsByUserName(user, userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userPersonPhysicalRequest);

        userPersonPhysicalMapper.update(user, userPersonPhysicalRequest);

        User save = userRepository.save(user);
        log.debug("PUT update userId saved {} ", user.getId());
        log.info("User update successfully userId {} ", user.getId());
        return userPersonPhysicalMapper.toResponse(save);
    }

    @Override
    @Transactional
    public UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(UUID personId) {
        Optional<User> userOptional = userRepository.findByPersonIdUserDto(personId);

        if (userOptional.isPresent()) {
            return userPersonPhysicalMapper.toResponse(userOptional.get());
        }
        log.debug("GET UUID personId received {} ", userOptional);
        return userPersonPhysicalMapper.toResponse(new User());
    }

    @Override
    @Transactional
    public UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest) {
        return userService.createPersonUser(personId, userAddPersonRequest, PersonType.PHYSICAL);
    }

    @Override
    @Transactional
    public void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("Verify password {} ", user.getId());
        if (!user.getPassword().equals(userPersonPhysicalRequest.getPassword())) {
            userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));
        }
    }

}
