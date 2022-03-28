package br.com.projeto.authjwt.api.response;

import lombok.Data;

@Data
public class PermissionResponse {

    private Long permissionId;

    private String nome;

    private String descricao;

}
