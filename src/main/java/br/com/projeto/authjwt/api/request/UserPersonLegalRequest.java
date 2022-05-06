package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.models.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserPersonLegalRequest {

    private String username;

    private String password;

    private PersonLegalRequest person;

    Set<Role> roles = new HashSet<>();

}
