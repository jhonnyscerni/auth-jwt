package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.service.PersonLegalService;
import java.util.List;
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

    @PostMapping
    public ResponseEntity<PersonLegalResponse> create(@RequestBody PersonLegalRequest personLegalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personLegalService.create(personLegalRequest));
    }

    @PutMapping("/{personphisicalId}")
    public ResponseEntity<PersonLegalResponse> update(@PathVariable Long personphisicalId,
        @RequestBody @Valid PersonLegalRequest personLegalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(personLegalService.update(personphisicalId, personLegalRequest));
    }

    @DeleteMapping("/{personphisicalId}")
    public ResponseEntity<Void> delete(@PathVariable Long personphisicalId) {
        personLegalService.delete(personphisicalId);
        return ResponseEntity.noContent().build();
    }
}
