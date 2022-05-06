package br.com.projeto.authjwt.utils;

import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LogicVerifyPersonTypeLogin {

    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;

    public void setPersonTypePersonPhysicalRequest(PersonPhysicalRequest personPhysicalRequest){

        User user = getLoggedUser();

        if (user.getPerson() instanceof PersonPhysical){
            personPhysicalRequest.setPersonPhysicalId(user.getPerson().getId());
            personPhysicalRequest.setPersonLegalId(null);
        } else if (user.getPerson() instanceof PersonLegal) {
            personPhysicalRequest.setPersonPhysicalId(null);
            personPhysicalRequest.setPersonLegalId(user.getPerson().getId());
        }
    }

    public void setPersonTypePersonPhysicalRequest(UserPersonPhysicalRequest personPhysicalRequest){

        User user = getLoggedUser();

        if (user.getPerson() instanceof PersonPhysical){
            personPhysicalRequest.getPerson().setPersonPhysicalId(user.getPerson().getId());
            personPhysicalRequest.getPerson().setPersonLegalId(null);
        } else if (user.getPerson() instanceof PersonLegal) {
            personPhysicalRequest.getPerson().setPersonPhysicalId(null);
            personPhysicalRequest.getPerson().setPersonLegalId(user.getPerson().getId());
        }
    }

    private User getLoggedUser() {
        UserDetails userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("Not found User Logged"));
    }

}
