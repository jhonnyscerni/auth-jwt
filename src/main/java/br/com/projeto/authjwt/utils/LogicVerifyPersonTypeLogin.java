package br.com.projeto.authjwt.utils;

import br.com.projeto.authjwt.api.request.PersonLegalRequest;
import br.com.projeto.authjwt.api.request.PersonPhysicalRequest;
import br.com.projeto.authjwt.api.request.UserPersonPhysicalRequest;
import br.com.projeto.authjwt.models.PersonLegal;
import br.com.projeto.authjwt.models.PersonPhysical;
import br.com.projeto.authjwt.models.User;
import br.com.projeto.authjwt.models.exceptions.EntityNotFoundException;
import br.com.projeto.authjwt.repositories.UserRepository;
import br.com.projeto.authjwt.security.UserDetailsImpl;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LogicVerifyPersonTypeLogin {

    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;

    public void setUserIdLoggedPerson(PersonPhysicalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.setUserId(user.getId());
    }

    public void setUserIdLoggedPerson(PersonLegalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.setUserId(user.getId());
    }

    public void setUserIdLoggedPerson(UserPersonPhysicalRequest personPhysicalRequest) {

        User user = getLoggedUser();
        personPhysicalRequest.getPerson().setUserId(user.getId());
    }

    public User getLoggedUser() {
        UserDetails userDetails = (UserDetailsImpl) authenticationFacade.getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new EntityNotFoundException("Not found User Logged"));
    }

}
