package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/person-phisical")
public class PersonPhysicalController {

    private final PersonPhysicalService personPhysicalService;

    @GetMapping
    public ResponseEntity<List<PersonPhysicalResponse>> list() {
        return ResponseEntity.ok().body(personPhysicalService.findAll());
    }

    @PostMapping(value = "/{personphisicalId}")
    public ResponseEntity<PersonPhysicalResponse> savePadrinho(
            @PathVariable Long personphisicalId, @RequestBody PersonPhysicalRequest personPhysicalRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personPhysicalService.create(personphisicalId, personPhysicalRequest));
    }
}
