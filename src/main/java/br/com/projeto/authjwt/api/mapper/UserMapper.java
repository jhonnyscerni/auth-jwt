package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.PersonResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    @Mapping(target = "person", ignore = true)
    void update(@MappingTarget User user, UserRequest userRequest);
}
