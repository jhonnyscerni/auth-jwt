package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.UserPersonLegalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonLegalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserPersonLegalFilter;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.RoleService;
import br.com.projeto.authjwt.service.UserPersonLegalService;
import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.utils.LogicVerifyPersonTypeLogin;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public UserResponse create(UserPersonLegalRequest userPersonLegalRequest) {
        log.debug("POST UserPersonLegalRequest userPersonLegalRequest received {} ", userPersonLegalRequest.toString());

        userService.existsByUserName(new User(), userPersonLegalRequest.getUsername());
        //existsByUserEmail(new User(), userRequest.getEmail());

        logicVerifyPersonTypeLogin.setUserIdLoggedPerson(userPersonLegalRequest);

        userPersonLegalRequest.setPassword(passwordEncoder.encode(userPersonLegalRequest.getPassword()));
        //userRequest.setUserType(UserType.USER.name());
        Role pessoas = roleService.findByRoleName(RoleType.ROLE_PESSOAS.name());
        userPersonLegalRequest.getRoles().add(pessoas);

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
        UUID userId = ((PersonLegal) user.getPerson()).getUserId();

        userService.existsByUserName(user, userPersonLegalRequest.getUsername());
        //existsByUserEmail(user, userRequest.getEmail());
        passwordNotEquals(user, userPersonLegalRequest);

        userPersonLegalMapper.update(user, userPersonLegalRequest);
        PersonLegal personLegal = (PersonLegal) user.getPerson();
        personLegal.setUserId(userId);
        user.setPerson(personLegal);

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

    @Override
    public Page<UserResponse> search(UserPersonLegalFilter filter, Pageable pageable) {
        log.debug("GET UserFilter filter received {} ", filter.toString());
        List<User> users = userRepository.findAllUserPersonLegal(filter, null);
        return new PageImpl<>(
            users, pageable, users.size()).map(userPersonLegalMapper::toResponse);

    }

    @Override
    public Page<UserResponse> searchMy(UserPersonLegalFilter filter, Pageable pageable) {
        List<User> users = userRepository.findAllUserPersonLegal(filter, logicVerifyPersonTypeLogin.getLoggedUser().getId());
        return new PageImpl<>(
            users, pageable, users.size()).map(userPersonLegalMapper::toResponse);
    }

}
