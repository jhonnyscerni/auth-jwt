package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.PersonPhysical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonPhysicalMapper {

    PersonPhysical create(PersonPhysicalRequest userRequest);

    void update(@MappingTarget PersonPhysical entity, PersonPhysicalRequest model);


    PersonPhysicalResponse toResponse(PersonPhysical entity);

    PersonPhysical toEntity(PersonPhysicalResponse model);

}
