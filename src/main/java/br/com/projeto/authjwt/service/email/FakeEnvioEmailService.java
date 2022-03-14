package br.com.projeto.authjwt.service.email;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEnvioEmailService extends SmtpEnvioEmailService {

    @Override
    public void enviar(Mensagem mensagem, SmtpTipoEnvioEmail smtpTipoEnvioEmail) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate
        // da classe pai para "protected", para poder chamar aqui
        String corpo = "";
        if (smtpTipoEnvioEmail.equals(SmtpTipoEnvioEmail.FREEMARKER)) {
            corpo = processarTemplate(mensagem);
        } else if (smtpTipoEnvioEmail.equals(SmtpTipoEnvioEmail.SIMPLES)) {
            corpo = mensagem.getCorpo();
        }

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }

}