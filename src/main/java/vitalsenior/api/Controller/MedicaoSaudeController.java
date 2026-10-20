package vitalsenior.api.Controller;

import vitalsenior.api.Domain.model.Usuario;
import vitalsenior.api.DTO.Request.RegistrarMedicaoDTO;
import vitalsenior.api.DTO.Response.MedicaoResponseDTO;
import vitalsenior.api.Service.MedicaoSaudeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medicoes")
@RequiredArgsConstructor
public class MedicaoSaudeController {

    private final MedicaoSaudeService medicaoService;

    @PostMapping
    public ResponseEntity<MedicaoResponseDTO> registrar(
            @AuthenticationPrincipal Usuario usuario,
            @RequestBody @Valid RegistrarMedicaoDTO dto
    ) {
        MedicaoResponseDTO response = medicaoService.registrarMedicao(usuario, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MedicaoResponseDTO>> listarHistorico(
            @AuthenticationPrincipal Usuario usuario
    ) {
        List<MedicaoResponseDTO> historico = medicaoService.listarHistorico(usuario);
        return ResponseEntity.ok(historico);
    }
}