package br.com.projeto.authjwt.models;

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

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column
    private String surname;

    @Column
    private String gender;

    @Column
    private String zoneVoting;

    @Column
    private String sectionVote;

    @OneToOne
    private PersonPhysical godfather;

    @OneToOne
    private PersonLegal company;

    @Column
    private String observation;
}
