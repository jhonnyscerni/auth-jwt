package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.enums.PersonType;
import java.util.List;
import java.util.UUID;

public interface PersonPhysicalService {

    PersonPhysical buscarOuFalhar(UUID personphysicalId);

    PersonPhysicalResponse create(UUID personphysicalId, PersonPhysicalRequest personPhysicalRequest);

    PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest);

    List<PersonPhysicalResponse> findAll();

    PersonPhysicalResponse update(UUID personphisicalId, PersonPhysicalRequest personPhysicalRequest);

    void delete(UUID id);

    PersonPhysicalResponse findByIdResponse(UUID personphisicalId);

    List<PersonPhysicalResponse> findAllMy(UUID userId);

    Long countPersonPhysical(UUID aLong);

    Long countPersonPhysicalVoteIsConquistado(UUID aLong);

    Long countPersonPhysicalVoteIsAConquistar(UUID aLong);

    Long countPersonPhysicalVoteIsPerdido(UUID aLong);
}
