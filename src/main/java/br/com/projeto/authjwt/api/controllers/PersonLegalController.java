package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.service.PersonLegalService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("/persons-legal")
public class PersonLegalController {

    private final PersonLegalService personLegalService;

    @GetMapping
    public ResponseEntity<List<PersonLegalResponse>> list() {
        return ResponseEntity.ok().body(personLegalService.findAll());
    }

    @GetMapping("/{personLegalId}")
    public ResponseEntity<PersonLegalResponse> findById(@PathVariable UUID personLegalId) {
        return ResponseEntity.ok().body(personLegalService.findByIdResponse(personLegalId));
    }

    @PostMapping
    public ResponseEntity<PersonLegalResponse> create(@RequestBody PersonLegalRequest personLegalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personLegalService.create(personLegalRequest));
    }

    @PutMapping("/{personLegalId}")
    public ResponseEntity<PersonLegalResponse> update(@PathVariable UUID personLegalId,
        @RequestBody @Valid PersonLegalRequest personLegalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(personLegalService.update(personLegalId, personLegalRequest));
    }

    @DeleteMapping("/{personLegalId}")
    public ResponseEntity<Void> delete(@PathVariable UUID personLegalId) {
        personLegalService.delete(personLegalId);
        return ResponseEntity.noContent().build();
    }
}
