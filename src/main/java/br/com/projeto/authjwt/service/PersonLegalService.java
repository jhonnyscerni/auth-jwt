package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import java.util.List;
import java.util.UUID;

public interface PersonLegalService {

    PersonLegal buscarOuFalhar(UUID personlegalId);

    PersonLegalResponse create(PersonLegalRequest personLegalRequest);

    List<PersonLegalResponse> findAll();

    PersonLegalResponse update(UUID personLegalId, PersonLegalRequest personLegalRequest);

    void delete(UUID personLegalId);

    PersonLegalResponse findByIdResponse(UUID personLegalId);
}
