package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Person;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String password;

    private Person person;

    private Set<RoleResponse> roles = new HashSet<>();

}
