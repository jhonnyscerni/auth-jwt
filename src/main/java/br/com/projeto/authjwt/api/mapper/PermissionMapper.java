package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends ModelMapper<Permission, PermissionResponse> {

}
