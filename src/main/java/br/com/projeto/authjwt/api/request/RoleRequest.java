package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.models.Permission;
import br.com.projeto.authjwt.models.enums.RoleType;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleRequest {

    private RoleType roleName;

    private List<Permission> permissions = new ArrayList<>();
}
