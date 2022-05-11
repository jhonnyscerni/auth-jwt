package br.com.projeto.authjwt.repositories.impl;

import br.com.projeto.authjwt.filter.UserPersonLegalFilter;
import br.com.projeto.authjwt.filter.UserPersonPhysicalFilter;
import br.com.projeto.authjwt.models.User;
import java.util.List;
import java.util.UUID;

public interface UserRepositoryQueries {

    List<User> findAllUserPersonPhysical(UserPersonPhysicalFilter filter, UUID user);

    List<User> findAllUserPersonLegal(UserPersonLegalFilter filter, UUID user);
}
