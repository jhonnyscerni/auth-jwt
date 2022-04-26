package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper extends ModelMapper<User, UserResponse> {

    User create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    //@Mapping(target = "id", ignore = true)
    //@Mapping(target = "person", expression = "java(model.getPerson())")
    void update(@MappingTarget User entity, UserPersonPhysicalRequest model);

    UserResponse toResponse(User entity);
}
