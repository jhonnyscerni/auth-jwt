package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends ModelMapper<User, UserResponse> {

    User create(UserRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget User entity, UserRequest model);
}
