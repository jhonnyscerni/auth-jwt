package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String password;

    private String fullName;

    private String phoneNumber;

    private String cpf;

    private Set<Role> roles = new HashSet<>();

}
