package br.com.projeto.authjwt.api.response;

import br.com.projeto.authjwt.models.enums.VoteEnum;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonPhysicalResponse {

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private VoteEnum vote;

    private AddressResponse address;

    private String cpf;

    private Date birthDate;

    private String surname;

    private String gender;

    private String zoneVoting;

    private String sectionVote;

    private String observation;

    private Long personPhysicalId;

    private Long personLedalId;

}