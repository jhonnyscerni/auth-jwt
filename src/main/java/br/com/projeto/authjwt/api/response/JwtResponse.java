package br.com.projeto.authjwt.api.response;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtResponse {

    @NonNull
    private String token;
    private String type = "Bearer";

}
