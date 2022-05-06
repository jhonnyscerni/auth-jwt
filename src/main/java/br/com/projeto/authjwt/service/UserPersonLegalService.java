package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonLegalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface UserPersonLegalService {

    UserResponse findByPersonLegalIdUserUserPersonLegalResponse(UUID personId);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest);

    UserResponse create(UserPersonLegalRequest userPersonLegalRequest);

    UserResponse update(UUID id, UserPersonLegalRequest userPersonLegalRequest);

    @Transactional
    void passwordNotEquals(User user, UserPersonLegalRequest userPersonLegalRequest);
}
