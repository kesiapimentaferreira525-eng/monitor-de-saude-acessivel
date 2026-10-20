package vitalsenior.api.Domain.repository;

import vitalsenior.api.Domain.model.Perfil;
import vitalsenior.api.Domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Usuario> findByPerfil(Perfil perfil);
}
