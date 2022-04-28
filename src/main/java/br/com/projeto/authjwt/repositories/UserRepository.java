package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByPersonEmail(String email);

    @Query("select u from User u join u.person p where p.id =:personId")
    Optional<User> findByPersonIdUserDto(@Param("personId") Long personId);
}
