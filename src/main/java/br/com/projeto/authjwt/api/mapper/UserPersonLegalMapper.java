package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersonLegalMapper {


    User add(UserAddPersonRequest userPersonPhysicalRequest);

    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    @Mapping(target = "person", expression = "java( toEntityRequest(model.getPerson()))")
    void update(@MappingTarget User entity, UserPersonLegalRequest model);

    @InheritConfiguration
    @Mapping(target = "person", expression = "java( toEntityRequest(userPersonPhysicalRequest.getPerson()))")
    User create(UserPersonLegalRequest userPersonPhysicalRequest);


    PersonLegal toEntityRequest(PersonLegalRequest model);

}
