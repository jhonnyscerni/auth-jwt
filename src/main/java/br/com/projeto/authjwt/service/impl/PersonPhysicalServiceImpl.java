package br.com.projeto.authjwt.service.impl;

import br.com.projeto.authjwt.api.mapper.PersonPhysicalMapper;
import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.PersonPhysicalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.enums.PersonType;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PersonPhysicalRepository;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.security.UserDetailsImpl;
import br.com.projeto.authjwt.service.PersonLegalService;
import br.com.projeto.authjwt.service.PersonPhysicalService;

import br.com.projeto.authjwt.service.UserService;
import br.com.projeto.authjwt.utils.AuthenticationFacade;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class PersonPhysicalServiceImpl implements PersonPhysicalService {

    private final PersonPhysicalRepository personPhysicalRepository;

    private final PersonLegalService personLegalService;
    private final PersonPhysicalMapper personPhysicalMapper;

    private final UserRepository userRepository;

    private final UserService userService;

    private final AuthenticationFacade authenticationFacade;

    private static final String MSG_OBJECT_IN_USE
        = "Person Physical %d cannot be removed as it is in use";


    @Override
    public PersonPhysical buscarOuFalhar(UUID personphysicalId) {
        log.debug("GET UUID personphysicalId received {} ", personphysicalId.toString());
        return personPhysicalRepository.findById(personphysicalId)
            .orElseThrow(() -> new EntityNotFoundException("There is no record of person physical", personphysicalId));
    }

    @Override
    public PersonPhysicalResponse create(UUID id, PersonPhysicalRequest personPhysicalRequest, PersonType personType) {
        log.debug("POST UUID roleRequest {} ", id.toString());
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());
        log.debug("POST String tipoPerson {} ", personType.name());
        PersonPhysical personPhysical = new PersonPhysical();
        if (Objects.equals(personType, PersonType.LEGAL)) {
            log.debug("String tipoPerson {} ", personType.name());
            personPhysicalRequest.setPersonLegalId(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysicalRepository.save(personPhysical);
            log.debug("POST create PersonPhysical saved set Gold Father in Company {} ", personPhysical.getName());
            log.info("PersonPhysical create successfully PersonPhysical {} ", personPhysical.getId());
        } else {
            log.debug("String tipoPerson id {} ", personType.name());
            personPhysicalRequest.setPersonPhysicalId(id);
            personPhysical = personPhysicalMapper.create(personPhysicalRequest);
            personPhysicalRepository.save(personPhysical);
            log.debug("POST create PersonPhysical saved set Gold Father in set PersonPhysical {} ", personPhysical.getName());
            log.info("PersonPhysical create successfully PersonPhysical {} ", personPhysical.getId());
        }

        return personPhysicalMapper.toResponse(personPhysical);
    }

    @Override
    @Transactional
    public PersonPhysicalResponse create(PersonPhysicalRequest personPhysicalRequest) {
        log.debug("POST PersonPhysicalRequest personPhysicalRequest {} ", personPhysicalRequest.toString());
        UserDetails userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("Not found User"));

        personPhysicalRequest.setPersonPhysicalId(user.getPerson().getId());
        personPhysicalRequest.setPersonLegalId(null);

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
    public PersonPhysicalResponse update(UUID personphisicalId, PersonPhysicalRequest personPhysicalRequest) {
        log.debug("PUT UUID personphisicalId received {} ", personphisicalId.toString());
        log.debug("PUT PersonPhysicalRequest personPhysicalRequest received {} ", personPhysicalRequest.toString());
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);

        personPhysicalMapper.update(personPhysical, personPhysicalRequest);
        PersonPhysical save = personPhysicalRepository.save(personPhysical);

        log.debug("PUT update personphisicalId saved {} ", personphisicalId);
        log.info("PersonPhysical update successfully personphisicalId {} ", personphisicalId);
        return personPhysicalMapper.toResponse(save);
    }

    @Override
    public void delete(UUID id) {
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
    public PersonPhysicalResponse findByIdResponse(UUID personphisicalId) {
        log.debug("GET findByIdResponse UUID personphisicalId {} ", personphisicalId);
        PersonPhysical personPhysical = buscarOuFalhar(personphisicalId);
        return personPhysicalMapper.toResponse(personPhysical);
    }

}
