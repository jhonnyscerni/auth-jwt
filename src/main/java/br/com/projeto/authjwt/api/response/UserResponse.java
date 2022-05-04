package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person person;

    private Set<RoleResponse> roles = new HashSet<>();

}
