package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.PersonLegal;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonLegalRepository extends JpaRepository<PersonLegal, UUID> {

    @Query("select pf from PersonLegal pf where pf.userId =:userId ")
    List<PersonLegal> findAllMy(UUID userId);
}
