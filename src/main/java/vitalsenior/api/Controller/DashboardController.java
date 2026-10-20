package vitalsenior.api.Controller;

import vitalsenior.api.Domain.model.Usuario;
import vitalsenior.api.Domain.repository.MedicaoSaudeRepository;
import vitalsenior.api.DTO.Response.DashboardResumo;
import vitalsenior.api.DTO.Response.MedicaoResponseDTO;
import vitalsenior.api.Service.MedicaoSaudeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final MedicaoSaudeRepository medicaoRepository;
    private final MedicaoSaudeService medicaoService;

    @GetMapping
    public ResponseEntity<DashboardResumo> resumo(@AuthenticationPrincipal Usuario usuario) {
        long total = medicaoRepository.countByUsuarioId(usuario.getId());
        MedicaoResponseDTO ultima = medicaoRepository
                .findFirstByUsuarioIdOrderByDataHoraMedicaoDesc(usuario.getId())
                .map(medicaoService::converterParaDTO)
                .orElse(null);

        return ResponseEntity.ok(DashboardResumo.builder()
                .totalMedicoes(total)
                .ultimaMedicao(ultima)
                .build());
    }
}
