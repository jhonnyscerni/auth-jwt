package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import br.com.projeto.authjwt.utils.LogicVerifyPersonTypeLogin;
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
@RequestMapping("/persons-phisical")
public class PersonPhysicalController {

    private final PersonPhysicalService personPhysicalService;

    private final LogicVerifyPersonTypeLogin logicVerifyPersonTypeLogin;

    @GetMapping
    public ResponseEntity<List<PersonPhysicalResponse>> list() {
        return ResponseEntity.ok().body(personPhysicalService.findAll());
    }

    @GetMapping("/my")
    public ResponseEntity<List<PersonPhysicalResponse>> listMy() {
        return ResponseEntity.ok()
            .body(personPhysicalService.findAllMy(
                logicVerifyPersonTypeLogin.getLoggedUser().getId()));
    }

    @PostMapping
    public ResponseEntity<PersonPhysicalResponse> create(@RequestBody PersonPhysicalRequest personPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personPhysicalService.create(personPhysicalRequest));
    }

    @GetMapping("/{personphisicalId}")
    public ResponseEntity<PersonPhysicalResponse> findById(@PathVariable UUID personphisicalId) {
        return ResponseEntity.ok().body(personPhysicalService.findByIdResponse(personphisicalId));
    }

    @PutMapping("/{personphisicalId}")
    public ResponseEntity<PersonPhysicalResponse> update(@PathVariable UUID personphisicalId,
        @RequestBody @Valid PersonPhysicalRequest personPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(personPhysicalService.update(personphisicalId, personPhysicalRequest));
    }

    @DeleteMapping("/{personphisicalId}")
    public ResponseEntity<Void> delete(@PathVariable UUID personphisicalId) {
        personPhysicalService.delete(personphisicalId);
        return ResponseEntity.noContent().build();
    }
}
