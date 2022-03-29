package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PermissionMapper;
import br.com.projeto.authjwt.api.request.PermissionRequest;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.filter.PermissionFilter;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PermissionRepository;
import br.com.projeto.authjwt.repositories.specs.PermissionSpecification;
import br.com.projeto.authjwt.service.PermissionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionServiceImp implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    private static final String MSG_PERMISSAO_EM_USO
        = "Permission de código %d não pode ser removida, pois está em uso";


    @Override
    public Permission buscarOuFalhar(Long permisionId) {
        return permissionRepository.findById(permisionId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de permission"));
    }

    @Override
    public List<PermissionResponse> findAll() {
        return permissionRepository.findAll().stream().map(permissionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PermissionResponse findByIdPermisionResponse(Long id) {
        Permission permission = buscarOuFalhar(id);
        return permissionMapper.toResponse(permission);
    }

    @Override
    public Page<PermissionResponse> search(PermissionFilter filter, Pageable pageable) {
        return permissionRepository.findAll(new PermissionSpecification(filter), pageable).map(permissionMapper::toResponse);
    }

    @Override
    public PermissionResponse create(PermissionRequest permissionRequest) {
        Permission permission = permissionMapper.create(permissionRequest);
        permissionRepository.save(permission);
        return permissionMapper.toResponse(permission);
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequest roleRequest) {
        Permission permission = buscarOuFalhar(id);

        permissionMapper.update(permission, roleRequest);
        return permissionMapper.toResponse(permissionRepository.save(permission));
    }


    @Override
    public void delete(Long id) {
        try {
            permissionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Permission não encontrado");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }

}
