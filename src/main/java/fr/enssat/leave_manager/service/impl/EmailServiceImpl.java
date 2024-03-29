package fr.enssat.leave_manager.service.impl;

import fr.enssat.leave_manager.model.Mail;
import fr.enssat.leave_manager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());


            Context context = new Context();

            mail.getModel().forEach((k, v) -> {
                context.setVariable(k, v);
            });
            //context.setVariable("test","hello");//mail.getModel());
            //System.out.println("test :"+context.getVariable("test"));
            //System.out.println("user"+context.getVariable("user"));
            String html = templateEngine.process("email/email-template", context);
            System.out.println(html);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
