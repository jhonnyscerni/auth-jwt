package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;

import java.util.List;
import java.util.UUID;

public interface PersonPhysicalService {

    PersonPhysical buscarOuFalhar(UUID personphysicalId);

    PersonPhysicalResponse create(UUID personphysicalId, PersonPhysicalRequest personPhysicalRequest, String tipoPerson);

    PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest);

    List<PersonPhysicalResponse> findAll();

    PersonPhysicalResponse update(UUID personphisicalId, PersonPhysicalRequest personPhysicalRequest);

    void delete(UUID id);

    PersonPhysicalResponse findByIdResponse(UUID personphisicalId);
}
