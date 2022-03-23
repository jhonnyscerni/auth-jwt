package br.com.projeto.authjwt.filter;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Setter
@Getter
public class UserFilter {

    private String userName;

    private String email;

//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoInicio;
//
//    @DateTimeFormat(iso = ISO.DATE_TIME)
//    private OffsetDateTime dataCriacaoFim;

}
