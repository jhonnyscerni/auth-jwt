package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.api.response.AddressResponse;
import br.com.projeto.authjwt.models.enums.VoteEnum;
import java.util.Date;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonLegalRequest {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cnpj;

}
