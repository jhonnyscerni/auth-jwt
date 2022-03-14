package br.com.projeto.authjwt.service.email;


import br.com.projeto.authjwt.core.email.EmailException;
import br.com.projeto.authjwt.core.email.EmailProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public void enviar(Mensagem mensagem, SmtpTipoEnvioEmail smtpTipoEnvioEmail) {
        try {
            MimeMessage mimeMessage = criarMimeMessage(mensagem, smtpTipoEnvioEmail);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Não foi possível enviar e-mail", e);
        }
    }

    protected MimeMessage criarMimeMessage(Mensagem mensagem, SmtpTipoEnvioEmail smtpTipoEnvioEmail) throws MessagingException {
        String corpo = "";
        if (smtpTipoEnvioEmail.equals(SmtpTipoEnvioEmail.FREEMARKER)) {
            corpo  = processarTemplate(mensagem);
        }else if (smtpTipoEnvioEmail.equals(SmtpTipoEnvioEmail.SIMPLES)){
            corpo = mensagem.getCorpo();
        }
        
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getRemetente());
        helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
        helper.setSubject(mensagem.getAssunto());
        helper.setText(corpo, true);

        return mimeMessage;
    }

    protected String processarTemplate(Mensagem mensagem) {
        try {
            Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());

            return FreeMarkerTemplateUtils.processTemplateIntoString(
                template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailException("Não foi possível montar o template do e-mail", e);
        }
    }

}