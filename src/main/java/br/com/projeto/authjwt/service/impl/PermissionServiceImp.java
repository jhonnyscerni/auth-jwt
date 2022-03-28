package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PermissionMapper;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PermissionRepository;
import br.com.projeto.authjwt.service.PermissionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PermissionServiceImp implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final PermissionMapper permissionMapper;

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

}
