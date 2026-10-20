package vitalsenior.api.DTO.Request;

import vitalsenior.api.Domain.model.Perfil;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CadastroUsuarioDTO {
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 120, message = "O nome não pode ultrapassar 120 caracteres")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotNull(message = "O perfil é obrigatório")
    private Perfil perfil;

    @Email(message = "E-mail do cuidador inválido")
    private String emailCuidador;
}