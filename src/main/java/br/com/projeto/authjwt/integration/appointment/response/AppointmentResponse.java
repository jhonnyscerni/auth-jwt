package br.com.projeto.authjwt.integration.appointment.response;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponse {

    private UUID id;

    private OffsetDateTime dateAppointment;

    private String locationService;

    private String comments;

    private OffsetDateTime start;

    private String title;

    private String className;
}
