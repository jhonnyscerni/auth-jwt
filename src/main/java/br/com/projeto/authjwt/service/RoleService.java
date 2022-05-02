package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.api.request.RoleRequest;
import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Role;
import java.util.List;

public interface RoleService {

    Role findByRoleName(String roleType);

    Role buscarOuFalhar(Long permisionId);

    List<RoleResponse> findAll();

    RoleResponse findByIdRoleResponse(Long id);

    RoleResponse create(RoleRequest userRequest);

    RoleResponse update(Long id, RoleRequest roleRequest);

    void delete(Long id);

    void connectPermission(Long roleId, Long permissionId);

    void disassociatePermission(Long roleId, Long permissionId);
}
