package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.models.Role;
import br.com.projeto.authjwt.models.enums.RoleType;
import br.com.projeto.authjwt.models.exceptions.BusinessException;
import br.com.projeto.authjwt.repositories.RoleRepository;
import br.com.projeto.authjwt.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleType roleType) {
        return roleRepository.findByRoleName(roleType).orElseThrow(() -> new BusinessException("Error: Role is Not Found."));
    }
}
