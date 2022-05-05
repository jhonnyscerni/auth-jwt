package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Role;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserPersonPhysicalResponse {

    private UUID id;

    private String username;

    private String password;

    private PersonResponse person;

    Set<RoleResponse> roles = new HashSet<>();


}
