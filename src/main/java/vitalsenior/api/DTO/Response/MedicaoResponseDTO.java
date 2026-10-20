package vitalsenior.api.DTO.Response;


import vitalsenior.api.Domain.model.StatusAlerta;
import vitalsenior.api.Domain.model.TurnoMedicao;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class MedicaoResponseDTO {
    private Long id;
    private Double glicose;
    private Integer pressaoSistolica;
    private Integer pressaoDiastolica;
    private TurnoMedicao turno;
    private StatusAlerta statusAlerta;
    private LocalDateTime dataHoraMedicao;
}
