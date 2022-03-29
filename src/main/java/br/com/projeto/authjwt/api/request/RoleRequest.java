package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.models.enums.RoleType;
import lombok.Data;

@Data
public class RoleRequest {

    private RoleType roleName;

}
