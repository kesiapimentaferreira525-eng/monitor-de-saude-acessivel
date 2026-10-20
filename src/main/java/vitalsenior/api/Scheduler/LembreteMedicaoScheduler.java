package vitalsenior.api.Scheduler;

import vitalsenior.api.Domain.model.TurnoMedicao;
import vitalsenior.api.Domain.model.Usuario;
import vitalsenior.api.Domain.repository.MedicaoSaudeRepository;
import vitalsenior.api.Domain.repository.UsuarioRepository;
import vitalsenior.api.Service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LembreteMedicaoScheduler {

    private final UsuarioRepository usuarioRepository;
    private final MedicaoSaudeRepository medicaoRepository;
    private final EmailService emailService;

    @Scheduled(cron = "0 0 12 * * *")
    public void verificarMedicoesDaManha() {
        verificarMedicoesPendentes(TurnoMedicao.MANHA);
    }

    @Scheduled(cron = "0 0 21 * * *")
    public void verificarMedicoesDaNoite() {
        verificarMedicoesPendentes(TurnoMedicao.NOITE);
    }

    private void verificarMedicoesPendentes(TurnoMedicao turno) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicio = hoje.atStartOfDay();
        LocalDateTime fim = hoje.plusDays(1).atStartOfDay();
        List<Usuario> pacientes = usuarioRepository.findByPerfil(vitalsenior.api.Domain.model.Perfil.PACIENTE);

        for (Usuario paciente : pacientes) {
            boolean medicaoRegistrada = medicaoRepository.existsByUsuarioIdAndTurnoAndDataHoraMedicaoBetween(
                    paciente.getId(), turno, inicio, fim);
            if (!medicaoRegistrada && paciente.getEmailCuidador() != null
                    && !paciente.getEmailCuidador().isBlank()) {
                emailService.enviarAlertaCritico(
                        paciente.getEmailCuidador(),
                        paciente.getNome(),
                        "Aviso automático: " + paciente.getNome()
                                + " ainda não registrou a medição de saúde do turno " + turno + " de hoje."
                );
            }
        }
    }
}