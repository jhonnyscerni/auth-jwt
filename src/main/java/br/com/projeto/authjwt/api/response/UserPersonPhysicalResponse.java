package br.com.projeto.authjwt.api.response;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserPersonPhysicalResponse {

    private UUID id;

    private String username;

    private String password;

    private PersonPhysicalResponse person;

    private Set<RoleResponse> roles = new HashSet<>();

}
