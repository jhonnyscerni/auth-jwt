package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.request.PermissionRequest;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends ModelMapper<Permission, PermissionResponse> {

    Permission create(PermissionRequest userRequest);

    //@Mapping(target = "id", ignore = true)
    void update(@MappingTarget Permission entity, PermissionRequest model);
}
