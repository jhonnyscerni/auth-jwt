package br.com.projeto.authjwt.filter;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Setter
@Getter
public class UserFilter {

    private String username;

    private String email;

    private UUID userId;

//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoInicio;
//
//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoFim;

}
