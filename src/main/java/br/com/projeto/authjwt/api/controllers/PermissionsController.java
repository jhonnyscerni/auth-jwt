package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.PermissionRequest;
import br.com.projeto.authjwt.api.response.PermissionResponse;
import br.com.projeto.authjwt.filter.PermissionFilter;
import br.com.projeto.authjwt.service.PermissionService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    private final PermissionService permissionService;

    @GetMapping
    public ResponseEntity<Page<PermissionResponse>> search(PermissionFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(permissionService.search(filter, pageable));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PermissionResponse>> list() {
        return ResponseEntity.ok().body(permissionService.findAll());
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<PermissionResponse> findById(@PathVariable UUID permissionId) {
        return ResponseEntity.ok().body(permissionService.findByIdPermisionResponse(permissionId));
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> create(@RequestBody @Valid PermissionRequest permissionRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.create(permissionRequest));
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<PermissionResponse> update(@PathVariable UUID permissionId,
        @RequestBody @Valid PermissionRequest permissionRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.update(permissionId, permissionRequest));
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<Void> delete(@PathVariable UUID permissionId) {
        permissionService.delete(permissionId);
        return ResponseEntity.noContent().build();
    }

}
