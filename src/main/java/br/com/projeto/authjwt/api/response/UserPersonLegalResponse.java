package br.com.projeto.authjwt.api.response;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserPersonLegalResponse {

    private UUID id;

    private String username;

    private String password;

    private PersonLegalResponse person;

    private Set<RoleResponse> roles = new HashSet<>();

}
