package vitalsenior.api.Service;

import vitalsenior.api.Domain.model.MedicaoSaude;
import vitalsenior.api.Domain.model.StatusAlerta;
import vitalsenior.api.Domain.model.Usuario;
import vitalsenior.api.Domain.repository.MedicaoSaudeRepository;
import vitalsenior.api.DTO.Request.RegistrarMedicaoDTO;
import vitalsenior.api.DTO.Response.MedicaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicaoSaudeService {

    private final MedicaoSaudeRepository medicaoRepository;
    private final MotorRegrasService motorRegrasService;
    private final EmailService emailService;

    @Transactional
    public MedicaoResponseDTO registrarMedicao(Usuario usuario, RegistrarMedicaoDTO dto) {
        StatusAlerta status = motorRegrasService.avaliarMedicao(dto);

        MedicaoSaude medicao = MedicaoSaude.builder()
                .usuario(usuario)
                .glicose(dto.getGlicose())
                .pressaoSistolica(dto.getPressaoSistolica())
                .pressaoDiastolica(dto.getPressaoDiastolica())
                .turno(dto.getTurno())
                .statusAlerta(status)
                .dataHoraMedicao(LocalDateTime.now())
                .build();

        medicaoRepository.save(medicao);

        if (status == StatusAlerta.CRITICO && usuario.getEmailCuidador() != null
                && !usuario.getEmailCuidador().isBlank()) {
            String detalhes = String.format("Glicose: %.1f mg/dL | Pressão: %d/%d mmHg | Turno: %s",
                    dto.getGlicose(), dto.getPressaoSistolica(), dto.getPressaoDiastolica(), dto.getTurno());
            emailService.enviarAlertaCritico(usuario.getEmailCuidador(), usuario.getNome(), detalhes);
        }

        return mapToDTO(medicao);
    }

    public List<MedicaoResponseDTO> listarHistorico(Usuario usuario) {
        return medicaoRepository.findByUsuarioIdOrderByDataHoraMedicaoDesc(usuario.getId())
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public MedicaoResponseDTO converterParaDTO(MedicaoSaude m) {
        return MedicaoResponseDTO.builder()
                .id(m.getId())
                .glicose(m.getGlicose())
                .pressaoSistolica(m.getPressaoSistolica())
                .pressaoDiastolica(m.getPressaoDiastolica())
                .turno(m.getTurno())
                .statusAlerta(m.getStatusAlerta())
                .dataHoraMedicao(m.getDataHoraMedicao())
                .build();
    }

    private MedicaoResponseDTO mapToDTO(MedicaoSaude medicao) {
        return converterParaDTO(medicao);
    }
}
