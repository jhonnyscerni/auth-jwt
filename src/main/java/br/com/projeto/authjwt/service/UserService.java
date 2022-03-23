package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.dto.UserDto;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto saveUser(UserDto userDto);

    UserDto resetPassword(String email);

    User buscarOuFalhar(Long usuarioId);

    User buscarOuFalharPorEmail(String email);

    Page<UserDto> search(UserFilter filter, Pageable pageable);

    UserDto findByIdUserDto(Long id);
}
