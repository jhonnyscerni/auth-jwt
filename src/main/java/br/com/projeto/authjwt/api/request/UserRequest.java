package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserRequest {

    private UUID id;

    private String username;

    private String password;

    private Set<RoleResponse> roles = new HashSet<>();

}
