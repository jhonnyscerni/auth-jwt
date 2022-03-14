package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.dto.UserDto;

public interface UserService {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto saveUser(UserDto userDto);

    UserDto resetPassword(String email);
}
