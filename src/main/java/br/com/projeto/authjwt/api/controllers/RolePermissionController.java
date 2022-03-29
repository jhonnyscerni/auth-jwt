package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/roles/{roleId}/permissions")
public class RolePermissionController {

    private final RoleService roleService;

    @PutMapping("/{permissionId}")
    public ResponseEntity<Void> connect(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.connectRole(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long roleId, @PathVariable Long permissionId) {
        roleService.disassociateRole(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }


}
