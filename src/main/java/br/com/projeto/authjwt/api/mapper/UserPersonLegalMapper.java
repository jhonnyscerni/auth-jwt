package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.response.PersonResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserPersonLegalMapper {


    User add(UserAddPersonRequest userPersonPhysicalRequest);

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);


}
