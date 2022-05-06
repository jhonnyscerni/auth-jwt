package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.service.UserPersonLegalService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users/person-legal")
public class UserPersonLegalController {

    private final UserPersonLegalService personLegalService;


    @GetMapping("/{personLegalId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable UUID personLegalId) {
        return ResponseEntity.ok().body(personLegalService.findByPersonLegalIdUserUserPersonLegalResponse(personLegalId));
    }

    @PostMapping("/{personLegalId}")
    public ResponseEntity<UserResponse> createPersonUser(@PathVariable UUID personLegalId,
        @RequestBody @Valid UserAddPersonRequest userAddPersonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(personLegalService.createPersonUser(personLegalId, userAddPersonRequest));
    }

}