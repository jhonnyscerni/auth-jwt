package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserPersonLegalResponse;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.service.UserService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> search(UserFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(userService.search(filter, pageable));
    }

    @GetMapping("/my")
    public ResponseEntity<Page<UserResponse>> searchMy(UserFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(userService.searchMy(filter, pageable));
    }

    @GetMapping("/{userId}/person-physical")
    public ResponseEntity<UserPersonPhysicalResponse> findByIdPersonPhysical(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.findByIdPersonPhysical(userId));
    }

    @GetMapping("/{userId}/person-legal")
    public ResponseEntity<UserPersonLegalResponse> findByIdPersonLegal(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.findByIdPersonLegal(userId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID userId) {
        return ResponseEntity.ok().body(userService.findById(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable UUID userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userId,
        @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, userRequest));
    }

}
