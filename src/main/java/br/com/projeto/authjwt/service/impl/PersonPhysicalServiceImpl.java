package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PersonPhysicalRepository;
import br.com.projeto.authjwt.service.PersonPhysicalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PersonPhysicalServiceImpl implements PersonPhysicalService {

    private final PersonPhysicalRepository personPhysicalRepository;
    private final PersonPhysicalMapper personPhysicalMapper;

    @Override
    public PersonPhysical buscarOuFalhar(Long personphysicalId) {
        return personPhysicalRepository.findById(personphysicalId)
                .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de person physical", personphysicalId));
    }

    @Override
    public PersonPhysicalResponse create(Long personphysicalId, PersonPhysicalRequest personPhysicalRequest) {
        PersonPhysical padrinho = buscarOuFalhar(personphysicalId);
        PersonPhysical personPhysical = personPhysicalMapper.create(personPhysicalRequest);
        personPhysical.setGodfather(padrinho);
        personPhysicalRepository.save(personPhysical);
        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public List<PersonPhysicalResponse> findAll() {
        return personPhysicalRepository.findAll().stream().map(personPhysicalMapper::toResponse).collect(Collectors.toList());
    }

}
