package br.com.projeto.authjwt.api.response;

import java.util.UUID;
import lombok.Data;

@Data
public class PersonResponse {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private String vote;

    private AddressResponse address;
}
