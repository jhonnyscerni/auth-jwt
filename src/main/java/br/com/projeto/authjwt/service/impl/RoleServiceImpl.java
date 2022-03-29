package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.RoleMapper;
import br.com.projeto.authjwt.api.request.RoleRequest;
import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.BusinessException;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.RoleRepository;
import br.com.projeto.authjwt.service.PermissionService;
import br.com.projeto.authjwt.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final RoleMapper roleMapper;

    private static final String MSG_PERMISSAO_EM_USO
        = "Role de código %d não pode ser removida, pois está em uso";


    @Override
    public Role findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType).orElseThrow(() -> new BusinessException("Error: Role is Not Found."));
    }

    @Override
    public Role buscarOuFalhar(Long roleId) {
        return roleRepository.findById(roleId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de role"));
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoleResponse findByIdRoleResponse(Long id) {
        Role role = buscarOuFalhar(id);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        Role role = roleMapper.create(roleRequest);
        roleRepository.save(role);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse update(Long id, RoleRequest roleRequest) {
        Role role = buscarOuFalhar(id);

        roleMapper.update(role, roleRequest);
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Role não encontrado");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }

    @Override
    public void connectRole(Long roleId, Long permissionId) {
        Role role = buscarOuFalhar(roleId);
        Permission permission = permissionService.buscarOuFalhar(permissionId);
        role.adicionarPermissao(permission);
        roleRepository.save(role);
    }

    @Override
    public void disassociateRole(Long roleId, Long permissionId) {
        Role role = buscarOuFalhar(roleId);
        Permission permission = permissionService.buscarOuFalhar(permissionId);
        role.removerPermissao(permission);
        roleRepository.save(role);
    }
}
