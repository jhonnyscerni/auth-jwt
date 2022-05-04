package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.service.RoleService;
import java.util.UUID;
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
    public ResponseEntity<Void> connect(@PathVariable UUID roleId, @PathVariable UUID permissionId) {
        roleService.connectPermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> disassociate(@PathVariable UUID roleId, @PathVariable UUID permissionId) {
        roleService.disassociatePermission(roleId, permissionId);
        return ResponseEntity.noContent().build();
    }


}
