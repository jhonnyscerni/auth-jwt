package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.models.Permission;
import java.util.List;

public interface PermissionService {

    Permission buscarOuFalhar(Long permisionId);

    List<PermissionResponse> findAll();

    PermissionResponse findByIdPermisionResponse(Long permissionId);
}
