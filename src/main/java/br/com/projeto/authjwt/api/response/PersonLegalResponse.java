package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.enums.VoteEnum;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonLegalResponse {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cnpj;

    private UUID userId;

}
