package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PersonLegalRepository;
import br.com.projeto.authjwt.repositories.PersonPhysicalRepository;
import br.com.projeto.authjwt.service.PersonLegalService;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PersonPhysicalServiceImpl implements PersonPhysicalService {

    private final PersonPhysicalRepository personPhysicalRepository;

    private final PersonLegalService personLegalService;
    private final PersonPhysicalMapper personPhysicalMapper;

    private static final String MSG_PERMISSAO_EM_USO
        = "Person de código %d não pode ser removida, pois está em uso";


    @Override
    public PersonPhysical buscarOuFalhar(Long personphysicalId) {
        return personPhysicalRepository.findById(personphysicalId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de person physical", personphysicalId));
    }

    @Override
    public PersonPhysicalResponse create(Long id, PersonPhysicalRequest personPhysicalRequest, String tipoPerson) {
        PersonLegal personLegalGoldFather = new PersonLegal();
        PersonPhysical personPhysicalGoldFather = new PersonPhysical();
        PersonPhysical personPhysical = new PersonPhysical();
        if (Objects.equals(tipoPerson, "LEGAL")) {
            personLegalGoldFather = personLegalService.buscarOuFalhar(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysical.setCompany(personLegalGoldFather);
            personPhysicalRepository.save(personPhysical);
        } else {
            personPhysicalGoldFather = buscarOuFalhar(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysical.setGodfather(personPhysicalGoldFather);
            personPhysicalRepository.save(personPhysical);
        }

        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest) {
        PersonPhysical padrinho = buscarOuFalhar(personPhysicalRequest.getPersonPhysicalId());
        PersonPhysical personPhysical = personPhysicalMapper.create(personPhysicalRequest);
        personPhysical.setGodfather(padrinho);
        personPhysicalRepository.save(personPhysical);
        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public List<PersonPhysicalResponse> findAll() {
        return personPhysicalRepository.findAll().stream().map(personPhysicalMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonPhysicalResponse update(Long personphisicalId, PersonPhysicalRequest personPhysicalRequest) {
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);

        personPhysicalMapper.update(personPhysical, personPhysicalRequest);
        return personPhysicalMapper.toResponse(personPhysicalRepository.save(personPhysical));
    }

    @Override
    public void delete(Long id) {
        try {
            personPhysicalRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Person Physical not found");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }

}
