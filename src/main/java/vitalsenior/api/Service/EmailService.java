package vitalsenior.api.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void enviarAlertaCritico(String destinatario, String nomePaciente, String detalhes) {
        if (destinatario == null || destinatario.isBlank()) return;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("[VitalSênior] ALERTA DE SAÚDE - Paciente: " + nomePaciente);
        message.setText("Olá,\n\nO sistema VitalSênior detectou uma medição com valores críticos para o paciente " + nomePaciente + ".\n\nDetalhes:\n" + detalhes + "\n\nPor favor, verifique a situação imediatamente.");

        mailSender.send(message);
    }
}
