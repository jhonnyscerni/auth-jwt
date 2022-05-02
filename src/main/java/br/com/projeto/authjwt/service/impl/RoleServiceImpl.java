package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.RoleMapper;
import br.com.projeto.authjwt.api.request.RoleRequest;
import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.exceptions.BusinessException;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.RoleRepository;
import br.com.projeto.authjwt.service.PermissionService;
import br.com.projeto.authjwt.service.RoleService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    private final RoleMapper roleMapper;

    private static final String MSG_OBJECT_IN_USE
        = "Role %d cannot be removed as it is in use";


    @Override
    public Role findByRoleName(String roleType) {
        log.debug("GET String roleType received {} ", roleType);
        return roleRepository.findByName(roleType).orElseThrow(() -> new BusinessException("Error: Role is Not Found."));
    }

    @Override
    public Role buscarOuFalhar(Long roleId) {
        log.debug("GET Long roleId received {} ", roleId.toString());
        return roleRepository.findById(roleId)
            .orElseThrow(() -> new EntityNotFoundException("Error: There is no role registration", roleId));
    }

    @Override
    public List<RoleResponse> findAll() {
        log.debug("GET RoleResponse findAll");
        return roleRepository.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public RoleResponse findByIdRoleResponse(Long id) {
        log.debug("POST RoleResponse Long id received {} ", id.toString());
        Role role = buscarOuFalhar(id);
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest roleRequest) {
        log.debug("POST RoleRequest roleRequest received {} ", roleRequest.toString());
        Role role = roleMapper.create(roleRequest);
        roleRepository.save(role);
        log.debug("POST create roleName saved {} ", roleRequest.getName());
        log.info("Role create successfully roleName {} ", roleRequest.getName());
        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse update(Long id, RoleRequest roleRequest) {
        log.debug("PUT Long roleId received {} ", id.toString());
        log.debug("PUT RoleRequest roleRequest received {} ", roleRequest.toString());
        Role role = buscarOuFalhar(id);

        roleMapper.update(role, roleRequest);
        Role save = roleRepository.save(role);
        log.debug("PUT update roleName saved {} ", roleRequest.getName());
        log.info("Role update successfully roleName {} ", roleRequest.getName());
        return roleMapper.toResponse(save);
    }

    @Override
    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.warn("Role {} not found", id);
            throw new EntityNotFoundException("Role not found");

        } catch (DataIntegrityViolationException e) {
            log.warn("Role {} cannot be removed as it is in use", id);
            throw new EntityInUseException(
                String.format(MSG_OBJECT_IN_USE, id));
        }
    }

    @Override
    public void connectPermission(Long roleId, Long permissionId) {
        Role role = buscarOuFalhar(roleId);
        Permission permission = permissionService.buscarOuFalhar(permissionId);
        role.adicionarPermissao(permission);
        log.debug("add in Permission permissionId {} ", permissionId);
        roleRepository.save(role);
        log.info("connectPermission successfully permissionId {} ",permissionId);
    }

    @Override
    public void disassociatePermission(Long roleId, Long permissionId) {
        Role role = buscarOuFalhar(roleId);
        Permission permission = permissionService.buscarOuFalhar(permissionId);
        role.removerPermissao(permission);
        log.debug("removed in Permission permissionId {} ", permissionId);
        roleRepository.save(role);
        log.info("disassociatePermission successfully permissionId {} ", permissionId);
    }
}
