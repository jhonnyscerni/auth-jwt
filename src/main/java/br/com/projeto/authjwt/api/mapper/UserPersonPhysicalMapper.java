package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserAddPersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.PersonResponse;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserPersonPhysicalMapper {

    @Mapping(target = "person", expression = "java( toEntityRequest(model.getPerson()))")
    void update(@MappingTarget User entity, UserPersonPhysicalRequest model);

    @InheritConfiguration
    @Mapping(target = "person", expression = "java( toEntityRequest(userPersonPhysicalRequest.getPerson()))")
    User create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    User add(UserAddPersonPhysicalRequest userPersonPhysicalRequest);


    @Mapping(target = "person", expression = "java(toPersonResponse(entity.getPerson()))")
    UserPersonPhysicalResponse toResponse(User entity);

    PersonResponse toPersonResponse(Person person);

    PersonPhysical toEntityRequest(PersonPhysicalRequest model);

}
