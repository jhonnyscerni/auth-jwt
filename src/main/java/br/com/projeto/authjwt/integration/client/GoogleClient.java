package br.com.projeto.authjwt.integration.client;

import br.com.projeto.authjwt.integration.appointment.response.AppointmentResponse;
import br.com.projeto.authjwt.integration.appointment.response.ResponsePageDto;
import br.com.projeto.authjwt.integration.client.dto.GoogleDTO;
import br.com.projeto.authjwt.models.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Component
public class GoogleClient {

    private final RestTemplate restTemplate;

    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    //257736fc-2fa4-4650-ad06-cd98964e346b

    public GoogleDTO getLatLong(Address address) {
        GoogleDTO searchResult = null;
        ResponseEntity<GoogleDTO> result = null;
        String url = URL + address.getStreet() + "," + address.getNumber() + "&key=AIzaSyDm1GMuzPBP5VjKierRwk7Uv6Mh94kPjaw";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>("parameters", headers);

        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);
        try {
            ParameterizedTypeReference<GoogleDTO> responseType = new ParameterizedTypeReference<GoogleDTO>() {
            };
            result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
            searchResult = result.getBody();
            log.debug("Response Number of Elements: {} ", searchResult.toString());
        } catch (HttpStatusCodeException e) {
            log.error("Error request /googleMaps {} ", e);
        }
        log.info("Ending request /googleMaps Adress {} ", address.toString());
        return result.getBody();
    }
}
