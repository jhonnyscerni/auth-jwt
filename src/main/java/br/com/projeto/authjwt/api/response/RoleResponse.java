package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Permission;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleResponse {

    private Long id;

    private String name;

    private List<Permission> permissions = new ArrayList<>();

}
