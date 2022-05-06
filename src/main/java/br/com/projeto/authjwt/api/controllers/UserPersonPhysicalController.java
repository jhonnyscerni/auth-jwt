package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.service.UserPersonPhysicalService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users/person-physical")
public class UserPersonPhysicalController {

    private final UserPersonPhysicalService userPersonPhysicalService;

    @GetMapping("/{personPhysicalId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable UUID personPhysicalId) {
        return ResponseEntity.ok().body(userPersonPhysicalService.findByPersonPhysicalIdUserUserPersonPhysicalResponse(personPhysicalId));
    }

    @PostMapping("/{personPhysicalId}")
    public ResponseEntity<UserResponse> createPersonUser(@PathVariable UUID personPhysicalId,
        @RequestBody @Valid UserAddPersonRequest userAddPersonRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(userPersonPhysicalService.createPersonUser(personPhysicalId, userAddPersonRequest));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userPersonPhysicalService.create(userPersonPhysicalRequest));
    }

    @PutMapping("/{userPersonPhysicalId}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID userPersonPhysicalId,
        @RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userPersonPhysicalService.update(userPersonPhysicalId, userPersonPhysicalRequest));
    }

}
