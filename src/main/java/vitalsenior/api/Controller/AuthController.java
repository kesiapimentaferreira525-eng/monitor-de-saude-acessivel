package vitalsenior.api.Controller;


import vitalsenior.api.DTO.Request.CadastroUsuarioDTO;
import vitalsenior.api.DTO.Request.LoginRequestDTO;
import vitalsenior.api.DTO.Response.JwtResponseDTO;
import vitalsenior.api.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroUsuarioDTO dto) {
        authService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody @Valid LoginRequestDTO dto) {
        JwtResponseDTO response = authService.autenticar(dto);
        return ResponseEntity.ok(response);
    }
}
