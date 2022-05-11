package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserPersonLegalResponse;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {


    void disassociateRole(UUID userId, UUID roleId);

    void connectRole(UUID userId, UUID roleId);

    User buscarOuFalhar(UUID usuarioId);

    UserResponse findById(UUID id);

    User buscarOuFalharPorEmail(String email);

    void delete(UUID id);

    @Transactional
    UserResponse resetPassword(String email);

    UserResponse update(UUID userId, UserRequest userRequest);

    void existsByUserName(User cliente, String username);

    void passwordNotEquals(User user, UserRequest userPersonPhysicalRequest);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest, PersonType personType);

    UserPersonPhysicalResponse findByIdPersonPhysical(UUID userId);

    UserPersonLegalResponse findByIdPersonLegal(UUID userId);
}
