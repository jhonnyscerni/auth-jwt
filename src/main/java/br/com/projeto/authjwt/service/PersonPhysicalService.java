package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;

import java.util.List;

public interface PersonPhysicalService {

    PersonPhysical buscarOuFalhar(Long personphysicalId);

    PersonPhysicalResponse create(Long personphysicalId, PersonPhysicalRequest personPhysicalRequest, String tipoPerson);

    PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest);

    List<PersonPhysicalResponse> findAll();

    PersonPhysicalResponse update(Long personphisicalId, PersonPhysicalRequest personPhysicalRequest);

    void delete(Long id);

    PersonPhysicalResponse findByIdResponse(Long personphisicalId);
}
