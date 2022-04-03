package br.com.projeto.authjwt.api.request;

import lombok.Data;

@Data
public class PermissionRequest {

    private String name;

    private String description;

}
