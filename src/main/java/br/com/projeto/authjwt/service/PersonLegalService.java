package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import java.util.List;

public interface PersonLegalService {

    PersonLegal buscarOuFalhar(Long personlegalId);

    PersonLegalResponse create(PersonLegalRequest personLegalRequest);

    List<PersonLegalResponse> findAll();

    PersonLegalResponse update(Long personphisicalId, PersonLegalRequest personLegalRequest);

    void delete(Long id);

    PersonLegalResponse findByIdResponse(Long empresaId);
}
