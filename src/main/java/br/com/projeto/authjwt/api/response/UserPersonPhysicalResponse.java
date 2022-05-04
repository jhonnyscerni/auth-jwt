package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;

@Data
public class UserPersonPhysicalResponse {

    private UUID id;

    private String username;

    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PersonPhysical person;

    Set<Role> roles = new HashSet<>();


}
