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

    @Query("SELECT count(pf.id) FROM PersonPhysical pf where pf.userId = :id")
    long countPersonPhysical(@Param("id") UUID id);

    @Query("SELECT count(pf.id) FROM PersonPhysical pf where pf.userId = :id and pf.vote = 'CONQUISTADO' ")
    long countPersonIsVoteIsConquistado(@Param("id") UUID id);

    @Query("SELECT count(pf.id) FROM PersonPhysical pf where pf.userId = :id and pf.vote = 'A_CONQUISTAR' ")
    long countPersonVoteIsAConquistar(@Param("id") UUID id);

    @Query("SELECT count(pf.id) FROM PersonPhysical pf where pf.userId = :id and pf.vote = 'PERDIDO' ")
    long countPersonVoteIsPerdido(@Param("id") UUID id);
}
