package ssm.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import ssm.core.service.MailService;
import ssm.core.service.TemplateService;

import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * @author jinqinghua@gmail.com
 * @version 2012/08/04
 */
public class MailServiceDefaultImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaMailSender mailSender;

    private TemplateService templateService;

    public MailServiceDefaultImpl() {
        // Do nothing
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }

    public void send(SimpleMailMessage simpleMailMessage) {
        mailSender.send(simpleMailMessage);
    }

    public void send(SimpleMailMessage mimeMessage, String templateName, Map<String, Object> model) {
        this.send(mimeMessage, templateName, model, "UTF-8");
    }

    public void send(SimpleMailMessage mimeMessage, String templateName, Map<String, Object> model, String encoding) {
        String content = templateService.getContent(templateName, model);
        MimeMessage mimeMsg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true, encoding);
            helper.setTo(mimeMessage.getTo());
            helper.setSubject(mimeMessage.getSubject());
            helper.setFrom(mimeMessage.getFrom());
            helper.setText(content, true);
            mailSender.send(mimeMsg);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
