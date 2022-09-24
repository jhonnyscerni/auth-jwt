package br.com.projeto.authjwt.service;

import br.com.projeto.authjwt.api.request.UserAddPersonRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.api.response.UserPersonPhysicalResponse;
import br.com.projeto.authjwt.api.response.UserResponse;
import br.com.projeto.authjwt.filter.UserFilter;
import br.com.projeto.authjwt.filter.UserPersonPhysicalFilter;
import br.com.projeto.authjwt.models.User;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserPersonPhysicalService {

    void passwordNotEquals(User user, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse create(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse update(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse findByPersonPhysicalIdUserUserPersonPhysicalResponse(UUID personId);

    Page<UserResponse> search(UserPersonPhysicalFilter filter, Pageable pageable);

    Page<UserResponse> searchMy(UserPersonPhysicalFilter filter, Pageable pageable);

    UserResponse createUserEvent(UserPersonPhysicalRequest userPersonPhysicalRequest);

    UserResponse updateUserEvent(UUID id, UserPersonPhysicalRequest userPersonPhysicalRequest);
}
