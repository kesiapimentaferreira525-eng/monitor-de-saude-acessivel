package vitalsenior.api.DTO.Request;

import vitalsenior.api.Domain.model.TurnoMedicao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistrarMedicaoDTO {
    @NotNull(message = "O valor da glicose é obrigatório")
    @Min(value = 20, message = "Valor de glicose inválido")
    @DecimalMax(value = "1000.0", message = "Valor de glicose inválido")
    private Double glicose;

    @NotNull(message = "A pressão sistólica é obrigatória")
    @Min(value = 50, message = "Pressão sistólica inválida")
    private Integer pressaoSistolica;

    @NotNull(message = "A pressão diastólica é obrigatória")
    @Min(value = 30, message = "Pressão diastólica inválida")
    private Integer pressaoDiastolica;

    @NotNull(message = "O turno da medição é obrigatório")
    private TurnoMedicao turno;
}