package vitalsenior.api.DTO.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResumo {

    private long totalMedicoes;
    private MedicaoResponseDTO ultimaMedicao;
}
