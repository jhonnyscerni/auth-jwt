package br.com.projeto.authjwt.models;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PERSON_PHYSICAL")
@PrimaryKeyJoinColumn(name = "id")
public class PersonPhysical extends Person {

    @Column(nullable = false)
    private String cpf;

    private LocalDate birthDate;

    @Column
    private String surname;

    @Column
    private String gender;

    @Column
    private String zoneVoting;

    @Column
    private String sectionVote;

    @Column
    private String observation;

    private UUID userId;
}
