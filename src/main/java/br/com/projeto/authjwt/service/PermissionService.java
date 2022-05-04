package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.api.request.PermissionRequest;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.filter.PermissionFilter;
import br.com.projeto.authjwt.models.Permission;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

    Permission buscarOuFalhar(UUID permisionId);

    List<PermissionResponse> findAll();

    PermissionResponse findByIdPermisionResponse(UUID permissionId);

    Page<PermissionResponse> search(PermissionFilter filter, Pageable pageable);

    PermissionResponse create(PermissionRequest permissionRequest);

    PermissionResponse update(UUID id, PermissionRequest roleRequest);

    void delete(UUID id);
}
