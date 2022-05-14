package br.com.projeto.authjwt.integration.appointment.clients;

import br.com.projeto.authjwt.integration.appointment.response.AppointmentResponse;
import br.com.projeto.authjwt.integration.appointment.response.ResponsePageDto;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@RequiredArgsConstructor
@Component
public class AppointmentClient {

    private final RestTemplate restTemplate;

    @Value("${api.url.appointment}")
    String REQUEST_URL_COURSE;

    public Page<AppointmentResponse> getAllCoursesByUser(UUID userId, Pageable pageable){
        List<AppointmentResponse> searchResult = null;
        ResponseEntity<ResponsePageDto<AppointmentResponse>> result = null;
        String url = REQUEST_URL_COURSE + "/appointment/appointments?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
            + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");

        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);
        try{
            ParameterizedTypeReference<ResponsePageDto<AppointmentResponse>> responseType = new ParameterizedTypeReference<ResponsePageDto<AppointmentResponse>>() {};
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e){
            log.error("Error request /appointments {} ", e);
        }
        log.info("Ending request /appointments userId {} ", userId);
        return result.getBody();
    }
}
