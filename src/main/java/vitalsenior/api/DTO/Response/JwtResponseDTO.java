package vitalsenior.api.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtResponseDTO {
    private String token;
    private String nome;
    private String email;
    private String perfil;
}