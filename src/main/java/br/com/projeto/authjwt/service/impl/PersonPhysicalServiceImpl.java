package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PersonPhysicalRepository;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.service.PersonLegalService;
import br.com.projeto.authjwt.service.PersonPhysicalService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PersonPhysicalServiceImpl implements PersonPhysicalService {

    private final PersonPhysicalRepository personPhysicalRepository;

    private final PersonLegalService personLegalService;
    private final PersonPhysicalMapper personPhysicalMapper;

    private final UserRepository userRepository;

    private static final String MSG_OBJECT_IN_USE
            = "Person Physical %d cannot be removed as it is in use";


    @Override
    public PersonPhysical buscarOuFalhar(Long personphysicalId) {
        log.debug("GET Long personphysicalId received {} ", personphysicalId.toString());
        return personPhysicalRepository.findById(personphysicalId)
                .orElseThrow(() -> new EntityNotFoundException("There is no record of person physical", personphysicalId));
    }

    @Override
    public PersonPhysicalResponse create(Long id, PersonPhysicalRequest personPhysicalRequest, String tipoPerson) {
        log.debug("POST Long roleRequest {} ", id.toString());
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());
        log.debug("POST String tipoPerson {} ", tipoPerson);
        PersonLegal personLegalGoldFather = new PersonLegal();
        PersonPhysical personPhysicalGoldFather = new PersonPhysical();
        PersonPhysical personPhysical = new PersonPhysical();
        if (Objects.equals(tipoPerson, "LEGAL")) {
            log.debug("String tipoPerson {} ", tipoPerson);
            personLegalGoldFather = personLegalService.buscarOuFalhar(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysical.setCompany(personLegalGoldFather);
            personPhysicalRepository.save(personPhysical);
            log.debug("POST create PersonPhysical saved set Gold Father in Company {} ",personLegalGoldFather.getName());
            log.info("PersonPhysical create successfully PersonPhysical {} ", personPhysical.getId());
        } else {
            log.debug("String tipoPerson id {} ", tipoPerson);
            personPhysicalGoldFather = buscarOuFalhar(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysical.setGodfather(personPhysicalGoldFather);
            personPhysicalRepository.save(personPhysical);
            log.debug("POST create PersonPhysical saved set Gold Father in set PersonPhysical {} ", personPhysicalGoldFather.getName());
            log.info("PersonPhysical create successfully PersonPhysical {} ", personPhysical.getId());
        }

        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest) {
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());
        //PersonPhysical padrinho = buscarOuFalhar(personPhysicalRequest.getPersonPhysicalId());
        PersonPhysical personPhysical = personPhysicalMapper.create(personPhysicalRequest);
        //personPhysical.setGodfather(padrinho);
        personPhysicalRepository.save(personPhysical);
        log.debug("POST create personPhysicalRequest id saved {} ", personPhysicalRequest.getId());
        log.info("PersonPhysical create successfully personPhysicalRequest id {} ", personPhysicalRequest.getId());
        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    public List<PersonPhysicalResponse> findAll() {
        log.debug("GET PersonPhysicalResponse findAll");
        return personPhysicalRepository.findAll().stream().map(personPhysicalMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonPhysicalResponse update(Long personphisicalId, PersonPhysicalRequest personPhysicalRequest) {
        log.debug("PUT Long personphisicalId received {} ", personphisicalId.toString());
        log.debug("PUT PersonPhysicalRequest personPhysicalRequest received {} ", personPhysicalRequest.toString());
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);

        personPhysicalMapper.update(personPhysical, personPhysicalRequest);
        PersonPhysical save = personPhysicalRepository.save(personPhysical);

        log.debug("PUT update personphisicalId saved {} ", personphisicalId);
        log.info("PersonPhysical update successfully personphisicalId {} ", personphisicalId);
        return personPhysicalMapper.toResponse(save);
    }

    @Override
    public void delete(Long id) {
        try {

            Optional<User> user = userRepository.findByPersonIdUserDto(id);
            if (user.isPresent()) {
                userRepository.delete(user.get());
            } else {
                personPhysicalRepository.deleteById(id);
            }

        } catch (EmptyResultDataAccessException e) {
            log.warn("Person Physical {} not found", id);
            throw new EntityNotFoundException("Person Physical not found");

        } catch (DataIntegrityViolationException e) {
            log.warn("Person Physical {} cannot be removed as it is in use", id);
            throw new EntityInUseException(
                    String.format(MSG_OBJECT_IN_USE, id));
        }
    }

    @Override
    public PersonPhysicalResponse findByIdResponse(Long personphisicalId) {
        log.debug("GET findByIdResponse Long personphisicalId {} ", personphisicalId);
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);
        return personPhysicalMapper.toResponse(personPhysical);
    }

}
