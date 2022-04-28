package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.service.UserService;
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

    @GetMapping
    public ResponseEntity<Page<UserResponse>> search(UserFilter filter,
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.ok().body(userService.search(filter, pageable));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findByIdUserDto(userId));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userPersonPhysicalRequest));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable Long userId,
        @RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, userPersonPhysicalRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/person/{personId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable Long personId) {
        return ResponseEntity.ok().body(userService.findByPersonIdUserDto(personId));
    }

    @PostMapping("/person/{personId}")
    public ResponseEntity<UserResponse> findByPersonId(@PathVariable Long personId,
        @RequestBody @Valid UserPersonPhysicalRequest userPersonPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createPersonUser(personId ,userPersonPhysicalRequest));
    }


}
