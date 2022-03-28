package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.enums.RoleType;
import java.util.List;

public interface RoleService {

    Role findByRoleName(RoleType roleType);

    List<RoleResponse> findAll();
}
