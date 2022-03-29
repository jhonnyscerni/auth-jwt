package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users/{userId}/roles")
public class UserRoleController {

    private final UserService userService;

    @PutMapping("/{roleId}")
    public ResponseEntity<Void> connect(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.connectRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> disassociate(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.disassociateRole(userId, roleId);
        return ResponseEntity.noContent().build();
    }


}
