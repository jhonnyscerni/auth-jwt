package br.com.projeto.authjwt.api.controllers;

import br.com.projeto.authjwt.integration.appointment.clients.AppointmentClient;
import br.com.projeto.authjwt.integration.appointment.response.AppointmentResponse;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class IntegrationUserAppointmentController {

    private final AppointmentClient appointmentClient;


    @GetMapping("/users/{userId}/appointments")
    public ResponseEntity<Page<AppointmentResponse>> search(
        @PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable pageable,
        @PathVariable(value = "userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(appointmentClient.getAllCoursesByUser(userId, pageable));
    }
}
