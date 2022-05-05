package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.models.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserAddPersonPhysicalRequest {

    private String username;

    private String password;

    Set<RoleRequest> roles = new HashSet<>();

}
