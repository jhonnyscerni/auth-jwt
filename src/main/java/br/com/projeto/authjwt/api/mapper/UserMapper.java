package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.UserAddPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = {PersonPhysicalMapper.class})
public interface UserMapper extends ModelMapper<User, UserResponse> {
    UserResponse toResponse(User entity);

    @Mapping(target = "person", ignore = true)
    void update(@MappingTarget User user, UserRequest userRequest);
}
