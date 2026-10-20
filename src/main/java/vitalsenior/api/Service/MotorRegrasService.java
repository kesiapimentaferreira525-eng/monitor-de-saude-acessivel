package vitalsenior.api.Service;


import vitalsenior.api.Domain.model.StatusAlerta;
import vitalsenior.api.DTO.Request.RegistrarMedicaoDTO;
import org.springframework.stereotype.Service;

@Service
public class MotorRegrasService {

    public StatusAlerta avaliarMedicao(RegistrarMedicaoDTO dto) {
        boolean glicoseCritica = dto.getGlicose() < 70 || dto.getGlicose() > 180;
        boolean pressaoCritica = dto.getPressaoSistolica() > 140 || dto.getPressaoDiastolica() > 90;

        if (glicoseCritica || pressaoCritica) {
            return StatusAlerta.CRITICO;
        }

        boolean glicoseAtencao = (dto.getGlicose() >= 140 && dto.getGlicose() <= 180) || (dto.getGlicose() >= 70 && dto.getGlicose() < 80);
        boolean pressaoAtencao = (dto.getPressaoSistolica() >= 130 && dto.getPressaoSistolica() <= 140) || (dto.getPressaoDiastolica() >= 85 && dto.getPressaoDiastolica() <= 90);

        if (glicoseAtencao || pressaoAtencao) {
            return StatusAlerta.ATENCAO;
        }

        return StatusAlerta.NORMAL;
    }
}
