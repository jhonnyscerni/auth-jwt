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

    private static final String MSG_OBJECT_IN_USE
        = "Permission %d cannot be removed as it is in use";


    @Override
    public Permission buscarOuFalhar(Long permisionId) {
        log.debug("GET Long permisionId received {} ", permisionId.toString());
        return permissionRepository.findById(permisionId)
            .orElseThrow(() -> new EntityNotFoundException("There is no perminssion registration ", permisionId));
    }

    @Override
    public List<PermissionResponse> findAll() {
        log.debug("GET PermissionResponse findAll");
        return permissionRepository.findAll().stream().map(permissionMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PermissionResponse findByIdPermisionResponse(Long id) {
        log.debug("GET Long permissionId received {} ", id.toString());
        Permission permission = buscarOuFalhar(id);
        return permissionMapper.toResponse(permission);
    }

    @Override
    public Page<PermissionResponse> search(PermissionFilter filter, Pageable pageable) {
        log.debug("GET PermissionFilter filter received {} ", filter.toString());
        return permissionRepository.findAll(new PermissionSpecification(filter), pageable).map(permissionMapper::toResponse);
    }

    @Override
    public PermissionResponse create(PermissionRequest permissionRequest) {
        log.debug("POST PermissionRequest permissionRequest received {} ", permissionRequest.toString());
        Permission permission = permissionMapper.create(permissionRequest);
        permissionRepository.save(permission);
        log.debug("POST create permissionId saved {} ", permission.getId());
        log.info("Permission create successfully permissionId {} ", permission.getId());
        return permissionMapper.toResponse(permission);
    }

    @Override
    public PermissionResponse update(Long id, PermissionRequest permissionRequest) {
        log.debug("PUT Long permissionId received {} ", id.toString());
        log.debug("PUT PermissionRequest roleRequest received {} ", permissionRequest.toString());
        Permission permission = buscarOuFalhar(id);

        permissionMapper.update(permission, permissionRequest);

        Permission save = permissionRepository.save(permission);
        log.debug("PUT update permissionId saved {} ", permission.getId());
        log.info("Permission update successfully permissionId {} ", permission.getId());
        return permissionMapper.toResponse(save);
    }


    @Override
    public void delete(Long id) {
        try {
            permissionRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Permission {} not found", id);
            throw new EntityNotFoundException("Permission não encontrado");

        } catch (DataIntegrityViolationException e) {
            log.warn("Permission {} cannot be removed as it is in use", id);
            throw new EntityInUseException(
                String.format(MSG_OBJECT_IN_USE, id));
        }
    }

}
