package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserRequest;
import br.com.projeto.authjwt.api.response.UserPersonLegalResponse;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.models.enums.PersonType;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

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

    //ADD PERSON USER
    @PostMapping("/person-physical/{personId}")
    public ResponseEntity<UserResponse> createPersonPhysicalUser(@PathVariable UUID personId,
        @RequestBody @Valid UserAddPersonRequest userAddPersonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createPersonUser(personId, userAddPersonRequest, PersonType.PHYSICAL));
    }

    @PostMapping("/person-legal/{personId}")
    public ResponseEntity<UserResponse> createPersonLegalUser(@PathVariable UUID personId,
        @RequestBody @Valid UserAddPersonRequest userAddPersonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.createPersonUser(personId, userAddPersonRequest, PersonType.LEGAL));
    }

    //UPDATE PERSON USERNAME, ROLES E PASSWORD
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userId,
        @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, userRequest));
    }

}
