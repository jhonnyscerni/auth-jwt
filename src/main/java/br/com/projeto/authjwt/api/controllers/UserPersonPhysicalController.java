package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.service.UserService;
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
@RequestMapping("/users")
public class UserPersonPhysicalController {

    private final UserService userService;

    @GetMapping("/person/{personId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(userService.findByPersonIdUserDto(personId));
    }

    @PostMapping("/person/{personId}")
    public ResponseEntity<UserResponse> createPersonUser(@PathVariable Long personId,
        @RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createPersonUser(personId ,userPersonPhysicalRequest));
    }


}
