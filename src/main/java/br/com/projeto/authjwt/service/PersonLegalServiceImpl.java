package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.mapper.PersonLegalMapper;
import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.response.PersonLegalResponse;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.exceptions.EntityInUseException;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.PersonLegalRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PersonLegalServiceImpl implements PersonLegalService {

    private final PersonLegalRepository personLegalRepository;
    private final PersonLegalMapper personLegalMapper;

    private static final String MSG_PERMISSAO_EM_USO
        = "Person de código %d não pode ser removida, pois está em uso";


    @Override
    public PersonLegal buscarOuFalhar(Long personlegalId) {
        return personLegalRepository.findById(personlegalId)
            .orElseThrow(() -> new EntityNotFoundException("Não existe um cadastro de person physical", personlegalId));
    }

    @Override
    public PersonLegalResponse create(PersonLegalRequest personLegalRequest) {
        PersonLegal personLegal = personLegalMapper.create(personLegalRequest);
        personLegalRepository.save(personLegal);
        return personLegalMapper.toResponse(personLegal);
    }

    @Override
    public List<PersonLegalResponse> findAll() {
        return personLegalRepository.findAll().stream().map(personLegalMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public PersonLegalResponse update(Long personphisicalId, PersonLegalRequest personLegalRequest) {
        PersonLegal personLegal = buscarOuFalhar(personphisicalId);

        personLegalMapper.update(personLegal, personLegalRequest);
        return personLegalMapper.toResponse(personLegalRepository.save(personLegal));
    }

    @Override
    public void delete(Long id) {
        try {
            personLegalRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Person Legal not found");

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                String.format(MSG_PERMISSAO_EM_USO, id));
        }
    }

    @Override
    public PersonLegalResponse findByIdResponse(Long empresaId) {
        PersonLegal personLegal = buscarOuFalhar(empresaId);
        return personLegalMapper.toResponse(personLegal);
    }

}
