package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import java.util.UUID;

public interface UserPersonPhysicalService {

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse update(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(UUID personId);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest);

}
