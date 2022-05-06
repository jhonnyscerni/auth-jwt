package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.UserPersonLegalMapper;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.UserPersonLegalService;
import br.com.projeto.authjwt.service.UserService;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@AllArgsConstructor
public class UserPersonLegalServiceImpl implements UserPersonLegalService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final UserPersonLegalMapper userPersonLegalMapper;


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

}
