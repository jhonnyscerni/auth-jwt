package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.api.response.AddressResponse;
import br.com.projeto.authjwt.models.enums.VoteEnum;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonLegalRequest {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cnpj;

}
