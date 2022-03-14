package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.enums.RoleType;

public interface RoleService {

    Role findByRoleName(RoleType roleType);
}
