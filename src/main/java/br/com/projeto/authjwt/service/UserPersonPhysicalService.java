package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;

public interface UserPersonPhysicalService {

    //void existsByUserEmail(User cliente, String email);

    void existsByUserName(User cliente, String username);

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserPersonPhysicalResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserPersonPhysicalResponse update(Long id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserPersonPhysicalResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(Long personId);

    UserPersonPhysicalResponse createPersonUser(Long personId, UserAddPersonPhysicalRequest userPersonPhysicalRequest);

}
