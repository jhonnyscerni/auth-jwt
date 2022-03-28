package br.com.projeto.authjwt.api.mapper;

import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.utils.ModelMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends ModelMapper<Role, RoleResponse> {

}
