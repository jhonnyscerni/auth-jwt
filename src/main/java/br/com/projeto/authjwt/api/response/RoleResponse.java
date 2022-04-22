package br.com.projeto.authjwt.api.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RoleResponse {

    private Long id;

    private String name;

    private List<PermissionResponse> permissions = new ArrayList<>();

}
