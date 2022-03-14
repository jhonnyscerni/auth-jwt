package br.com.projeto.authjwt.service.email;

import br.com.projeto.authjwt.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private EnvioEmailService envioEmailService;W

    public void sendNewPasswordEmail(User user) {
        EnvioEmailService.Mensagem mensagem = EnvioEmailService.Mensagem.builder()
                .assunto(user.getFullName() + " - Recuperação de senha")
                .corpo("modelo-recuperar-senha.html")
                .variavel("usuario", user)
                .variavel("novaSenha", user.getPassword())
                .destinatario(user.getEmail())
                .build();
        envioEmailService.enviar(mensagem, SmtpTipoEnvioEmail.SIMPLES);
    }
}
