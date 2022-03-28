package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.RoleMapper;
import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.BusinessException;
import br.com.projeto.authjwt.repositories.RoleRepository;
import br.com.projeto.authjwt.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public Role findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType).orElseThrow(() -> new BusinessException("Error: Role is Not Found."));
    }

    public List<RoleResponse> findAll(){
        return roleRepository.findAll().stream().map(roleMapper::toResponse).collect(Collectors.toList());
    }
}
