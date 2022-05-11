package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonLegalRequest;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserPersonLegalFilter;
import br.com.projeto.authjwt.filter.UserPersonPhysicalFilter;
import br.com.projeto.authjwt.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface UserPersonLegalService {

    UserResponse findByPersonLegalIdUserUserPersonLegalResponse(UUID personId);

    UserResponse createPersonUser(UUID personId, UserAddPersonRequest userAddPersonRequest);

    UserResponse create(UserPersonLegalRequest userPersonLegalRequest);

    UserResponse update(UUID id, UserPersonLegalRequest userPersonLegalRequest);

    @Transactional
    void passwordNotEquals(User user, UserPersonLegalRequest userPersonLegalRequest);

    Page<UserResponse> search(UserPersonLegalFilter filter, Pageable pageable);

    Page<UserResponse> searchMy(UserPersonLegalFilter filter, Pageable pageable);
}
