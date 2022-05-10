package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.enums.VoteEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonPhysicalResponse {

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

    private List<UserPersonResponse> users;

}
