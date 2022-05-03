package br.com.projeto.authjwt.service.impl;


import br.com.projeto.authjwt.api.mapper.UserPersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.ConflictException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserPersonPhysicalService;
import br.com.projeto.authjwt.service.UserService;
import java.util.Optional;
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

    private final PersonPhysicalService personPhysicalService;

    private final UserService userService;


    @Override
    @Transactional
    public UserPersonPhysicalResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST registerUser userDto received {} ", userPersonPhysicalRequest.toString());

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

        User user = userPersonPhysicalMapper.create(userPersonPhysicalRequest);
        user = userRepository.save(user);
        log.debug("POST registerUser userId saved {} ", user.getId());
        log.info("User saved successfully userId {} ", user.getId());

        return userPersonPhysicalMapper.toResponse(user);

    }

    @Override
    @Transactional
    public UserPersonPhysicalResponse update(Long id, UserPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("PUT Long id received {} ", id.toString());
        log.debug("PUT UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());
        User user = userService.buscarOuFalhar(id);

        existsByUserName(user, userPersonPhysicalRequest.getUsername());
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
    public UserPersonPhysicalResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(Long personId) {
        Optional<User> userOptional = userRepository.findByPersonIdUserDto(personId);

        if (userOptional.isPresent()) {
            return userPersonPhysicalMapper.toResponse(userOptional.get());
        }
        log.debug("GET Long personId received {} ", userOptional);
        return userPersonPhysicalMapper.toResponse(new User());
    }

    @Override
    @Transactional
    public UserPersonPhysicalResponse createPersonUser(Long personId, UserAddPersonPhysicalRequest userPersonPhysicalRequest) {
        log.debug("POST Long personId received {} ", personId.toString());
        log.debug("POST UserPersonPhysicalRequest userPersonPhysicalRequest received {} ", userPersonPhysicalRequest.toString());


        PersonPhysical personPhysical = personPhysicalService.buscarOuFalhar(personId);
        existsByUserName(new User(), userPersonPhysicalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());
        userPersonPhysicalRequest.setPassword(passwordEncoder.encode(userPersonPhysicalRequest.getPassword()));

        User user = userPersonPhysicalMapper.add(userPersonPhysicalRequest);
        user.setPerson(personPhysical);
        userRepository.save(user);
        log.debug("POST create userId saved {} ", user.getId());
        log.info("User create successfully userId {} ", user.getId());
        return userPersonPhysicalMapper.toResponse(user);
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
