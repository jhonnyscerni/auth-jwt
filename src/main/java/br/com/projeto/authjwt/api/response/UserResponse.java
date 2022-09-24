package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.integration.rabbitmq.user.UserEventResponse;
import br.com.projeto.authjwt.models.Person;
import br.com.projeto.authjwt.models.enums.UserStatus;
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

    private UserStatus userStatus;

    private PersonResponse person;

    private Set<RoleResponse> roles = new HashSet<>();

    public UserEventResponse convertToUserEventResponse(){
        UserEventResponse userEventResponse = new UserEventResponse();
        userEventResponse.setUserId(this.id);
        userEventResponse.setUsername(this.getUsername());
        userEventResponse.setName(this.getPerson().getName());
        userEventResponse.setEmail(this.getPerson().getEmail());
        userEventResponse.setPhoneNumber(this.getPerson().getPhoneNumber());
        userEventResponse.setVote(this.getPerson().getVote());
        userEventResponse.setUserStatus(this.getUserStatus().toString());
        return userEventResponse;
    }

}
