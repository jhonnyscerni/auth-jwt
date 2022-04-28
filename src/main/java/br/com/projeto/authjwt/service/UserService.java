package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    //void existsByUserEmail(User cliente, String email);

    void existsByUserName(User cliente, String username);

    void disassociateRole(Long userId, Long roleId);

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    void delete(Long id);

    UserResponse resetPassword(String email);

    User buscarOuFalhar(Long usuarioId);

    User buscarOuFalharPorEmail(String email);

    UserResponse findByIdUserDto(Long id);

    Page<UserResponse> search(UserFilter filter, Pageable pageable);

    UserResponse saveUser(UserPersonPhysicalRequest userResponse);

    UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse update(Long id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    void connectRole(Long userId, Long roleId);

    UserResponse findByPersonIdUserDto(Long personId);

    UserResponse createPersonUser(Long personId, UserPersonPhysicalRequest userPersonPhysicalRequest);
}
