package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonPhysicalMapper {

    PersonPhysical create(PersonPhysicalRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget PersonPhysical entity, PersonPhysicalRequest model);

    @Mapping(target="personPhysicalId",source = "entity.padrinho.id")
    @Mapping(target="personLedalId",source = "entity.empresa.id")
    PersonPhysicalResponse toResponse(PersonPhysical entity);

    PersonPhysical toEntity(PersonPhysicalResponse model);
}
