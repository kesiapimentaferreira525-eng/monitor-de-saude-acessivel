package vitalsenior.api.Domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "medicoes_saude")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicaoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private Double glicose; // em mg/dL

    @Column(nullable = false)
    private Integer pressaoSistolica; // ex: 120 mmHg

    @Column(nullable = false)
    private Integer pressaoDiastolica; // ex: 80 mmHg

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TurnoMedicao turno; // MANHA ou NOITE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAlerta statusAlerta;

    @Column(nullable = false)
    private LocalDateTime dataHoraMedicao;
}
