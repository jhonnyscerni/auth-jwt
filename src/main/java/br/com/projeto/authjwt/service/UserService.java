package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void existsByUserEmail(User cliente, String email);

    void existsByUserName(User cliente, String username);

    void disassociateRole(Long userId, Long roleId);

    void passwordNotEquals(User user, UserRequest userRequest);

    void delete(Long id);

    UserResponse resetPassword(String email);

    User buscarOuFalhar(Long usuarioId);

    User buscarOuFalharPorEmail(String email);

    UserResponse findByIdUserDto(Long id);

    Page<UserResponse> search(UserFilter filter, Pageable pageable);

    UserResponse saveUser(UserRequest userResponse);

    UserResponse create(UserRequest userRequest);

    UserResponse update(Long id, UserRequest userRequest);

    void connectRole(Long userId, Long roleId);
}
