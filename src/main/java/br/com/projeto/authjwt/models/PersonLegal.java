package br.com.projeto.authjwt.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PERSON_LEGAL")
@PrimaryKeyJoinColumn(name = "id")
public class PersonLegal extends Person {

    @Column(nullable = false)
    private String cnpj;

}
