package br.com.projeto.authjwt.integration.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleResultDTO implements Serializable {

    private GoogleGeometryDTO geometry;
}
