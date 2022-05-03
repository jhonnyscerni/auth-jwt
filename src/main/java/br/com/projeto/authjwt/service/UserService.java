package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {


    void disassociateRole(Long userId, Long roleId);

    void connectRole(Long userId, Long roleId);

    User buscarOuFalhar(Long usuarioId);

    UserResponse findById(Long id);

    User buscarOuFalharPorEmail(String email);

    Page<UserResponse> search(UserFilter filter, Pageable pageable);

    void delete(Long id);

    @Transactional
    UserResponse resetPassword(String email);

    UserResponse update(Long userId, UserRequest userRequest);

    void existsByUserName(User cliente, String username);

    void passwordNotEquals(User user, UserRequest userPersonPhysicalRequest);
}
