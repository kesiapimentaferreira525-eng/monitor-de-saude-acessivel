package vitalsenior.api.Service;

import vitalsenior.api.Domain.model.Usuario;
import vitalsenior.api.Domain.repository.UsuarioRepository;
import vitalsenior.api.DTO.Request.CadastroUsuarioDTO;
import vitalsenior.api.DTO.Request.LoginRequestDTO;
import vitalsenior.api.DTO.Response.JwtResponseDTO;
import vitalsenior.api.Exception.BusinessException;
import vitalsenior.api.Security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public void cadastrar(CadastroUsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new BusinessException("E-mail já cadastrado no sistema.");
        }

        Usuario usuario = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .perfil(dto.getPerfil())
                .emailCuidador(dto.getEmailCuidador())
                .build();

        usuarioRepository.save(usuario);
    }

    public JwtResponseDTO autenticar(LoginRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );

        Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado."));

        String token = jwtTokenProvider.generateToken(usuario);

        return new JwtResponseDTO(token, usuario.getNome(), usuario.getEmail(), usuario.getPerfil().name());
    }
}