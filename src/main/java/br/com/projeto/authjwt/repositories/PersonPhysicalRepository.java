package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.PersonPhysical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonPhysicalRepository extends JpaRepository<PersonPhysical, Long> {
}
