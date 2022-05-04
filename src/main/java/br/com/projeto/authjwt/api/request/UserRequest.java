package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.api.response.RoleResponse;
import br.com.projeto.authjwt.models.Person;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserRequest {

    private Long id;

    private String username;

    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Person person;

    private Set<RoleResponse> roles = new HashSet<>();

}