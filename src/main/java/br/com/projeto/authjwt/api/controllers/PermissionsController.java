package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.service.PermissionService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<List<PermissionResponse>> list() {
        return ResponseEntity.ok().body(permissionService.findAll());
    }


    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionResponse> findById(@PathVariable Long permissionId) {
        return ResponseEntity.ok().body(permissionService.findByIdPermisionResponse(permissionId));
    }

}
