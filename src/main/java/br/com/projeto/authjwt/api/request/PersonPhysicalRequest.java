package br.com.projeto.authjwt.api.request;

import br.com.projeto.authjwt.api.response.AddressResponse;
import br.com.projeto.authjwt.models.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonPhysicalRequest {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String surname;

    private String gender;

    private String zoneVoting;

    private String sectionVote;

    private String observation;

    private UUID userId;

}
