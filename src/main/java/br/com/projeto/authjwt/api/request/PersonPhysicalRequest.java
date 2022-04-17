package br.com.projeto.authjwt.api.request;

import lombok.Data;

import java.util.Date;

@Data
public class PersonPhysicalRequest {
    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private String cpf;

    private Date dataNascimento;

    private String apelido;

    private String genero;

    private String zonaVotacao;

    private String secaoVotacao;

    private String observacoes;
}
