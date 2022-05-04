package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, UUID> , JpaSpecificationExecutor<User> {

    @Query("select u from User u join fetch u.person p join fetch u.roles r join fetch r.permissions per where u.username =:username")
    Optional<User> findByUsername(@Param("username") String username);
    //Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    Optional<User> findByPersonEmail(String email);

    @Query("select u from User u join fetch u.person p where p.id =:personId")
    Optional<User> findByPersonIdUserDto(@Param("personId") UUID personId);
}
