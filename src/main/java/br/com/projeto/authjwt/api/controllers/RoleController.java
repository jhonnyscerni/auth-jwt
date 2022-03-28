package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.service.RoleService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponse>> list(){
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Long roleId) {
        return ResponseEntity.ok().body(roleService.findByIdRoleResponse(roleId));
    }
}
