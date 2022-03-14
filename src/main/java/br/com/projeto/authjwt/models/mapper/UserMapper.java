package br.com.projeto.authjwt.models.mapper;

import br.com.projeto.authjwt.dto.UserDto;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends ModelMapper<User, UserDto> {
}
