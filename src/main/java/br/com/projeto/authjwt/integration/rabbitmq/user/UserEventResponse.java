package br.com.projeto.authjwt.integration.rabbitmq.user;

import br.com.projeto.authjwt.models.enums.UserStatus;
import java.util.UUID;
import lombok.Data;

@Data
public class UserEventResponse {

    private UUID userId;

    private String username;

    private String name;

    private String email;

    private String phoneNumber;

    private String vote;

    private String userStatus;

    private String actionType;
}
