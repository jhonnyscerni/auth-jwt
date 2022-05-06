package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import java.util.UUID;

public interface UserPersonLegalService {

    UserResponse findByPersonLegalIdUserUserPersonLegalResponse(UUID personId);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest);

}
