package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonLegalMapper {

    PersonLegal create(PersonLegalRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget PersonLegal entity, PersonLegalRequest model);

    PersonLegalResponse toResponse(PersonLegal entity);

    PersonLegal toEntity(PersonLegalResponse model);
}
