package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Permission;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleResponse {

    private Long roleId;

    private String roleName;

    private List<Permission> permissions = new ArrayList<>();

}
