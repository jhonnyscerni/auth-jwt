package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    @Query("select r from Role r join fetch r.permissions p")
    List<Role> findAll();
}
