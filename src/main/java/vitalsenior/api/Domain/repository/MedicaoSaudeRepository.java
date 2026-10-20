package vitalsenior.api.Domain.repository;

import vitalsenior.api.Domain.model.MedicaoSaude;
import vitalsenior.api.Domain.model.TurnoMedicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MedicaoSaudeRepository extends JpaRepository<MedicaoSaude, Long> {

    List<MedicaoSaude> findByUsuarioIdOrderByDataHoraMedicaoDesc(Long usuarioId);

    Optional<MedicaoSaude> findFirstByUsuarioIdOrderByDataHoraMedicaoDesc(Long usuarioId);

    long countByUsuarioId(Long usuarioId);

    boolean existsByUsuarioIdAndTurnoAndDataHoraMedicaoBetween(
            Long usuarioId,
            TurnoMedicao turno,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
