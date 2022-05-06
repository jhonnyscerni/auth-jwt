package br.com.projeto.authjwt.repositories;

import br.com.projeto.authjwt.models.PersonPhysical;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonPhysicalRepository extends JpaRepository<PersonPhysical, UUID> {

    @Query("select pf from PersonPhysical pf where pf.userId =:userId ")
    List<PersonPhysical> findAllMy(@Param("userId") UUID userId);
}
