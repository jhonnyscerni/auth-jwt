package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;

public interface UserPersonPhysicalService {

    //void existsByUserEmail(User cliente, String email);

    void existsByUserName(User cliente, String username);

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse update(Long id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(Long personId);

    UserResponse createPersonUser(Long personId, UserAddPersonPhysicalRequest userPersonPhysicalRequest);
}
