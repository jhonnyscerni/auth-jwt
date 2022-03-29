package br.com.projeto.authjwt.service;


import br.com.projeto.authjwt.api.request.PermissionRequest;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.filter.PermissionFilter;
import br.com.projeto.authjwt.models.Permission;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

    Permission buscarOuFalhar(Long permisionId);

    List<PermissionResponse> findAll();

    PermissionResponse findByIdPermisionResponse(Long permissionId);

    Page<PermissionResponse> search(PermissionFilter filter, Pageable pageable);

    PermissionResponse create(PermissionRequest permissionRequest);

    PermissionResponse update(Long id, PermissionRequest roleRequest);

    void delete(Long id);
}
