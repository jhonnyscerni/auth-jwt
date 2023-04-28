package br.com.projeto.authjwt.integration.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleLocationDTO implements Serializable {

    private String lat;
    private String lng;
}
