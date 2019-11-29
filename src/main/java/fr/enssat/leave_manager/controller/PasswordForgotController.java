package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.PasswordForgotDto;
import fr.enssat.leave_manager.model.PasswordResetToken;
import fr.enssat.leave_manager.repository.PasswordResetTokenRepository;
import fr.enssat.leave_manager.service.EmailService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/reinitialisation-mot-de-passe")
public class PasswordForgotController {

    @Autowired
    private EmployeeService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SpringTemplateEngine templateEngine;
    Context context = new Context();

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgotPassword";
    }


    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {

        if (result.hasErrors()) {
            return "forgotPassword";
        }
        EmployeeEntity user = userService.getEmployeeByEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgotPassword";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        System.out.println(token.getToken());
        token.setUser(user);
        System.out.println(token.getUser());
        token.setExpiryDate(30);
        System.out.println(token.getExpiryDate());
        System.out.println(token.isExpired());
        tokenRepository.save(token);

        /*Mail mail = new Mail();
        mail.setFrom("no-reply@leavemanager.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Demande de réinitialisation de mot de passe");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://leave-manager.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/resetPassword?token=" + token.getToken());
        mail.setModel(model);
        System.out.println(mail.getModel());
        emailService.sendEmail(mail);*/

        Map <String, String> mailContent = new HashMap<>();
        mailContent.put("recipient", user.getEmail());
        mailContent.put("firstname", user.getFirstname());
        mailContent.put("subject", "Demande de réinitialisation de mot de passe");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        mailContent.put("resetUrl", url + "/resetPassword/pwd/?token=" + token.getToken());
        mailContent.put("templateId", "1107554");

        MailSender.sendMail(mailContent);

        return "redirect:/reinitialisation-mot-de-passe?success";

    }

}
