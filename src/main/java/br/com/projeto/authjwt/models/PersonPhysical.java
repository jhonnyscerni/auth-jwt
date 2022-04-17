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
    private Date dataNascimento;

    @Column
    private String apelido;

    @Column
    private String genero;

    @Column
    private String zonaVotacao;

    @Column
    private String secaoVotacao;

    @OneToOne
    private PersonPhysical padrinho;

    @OneToOne
    private PersonLegal empresa;

    @Column
    private String observacoes;
}
