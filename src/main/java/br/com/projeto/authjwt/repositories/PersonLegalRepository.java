package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.PersonLegal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonLegalRepository extends JpaRepository<PersonLegal, Long> {
}
