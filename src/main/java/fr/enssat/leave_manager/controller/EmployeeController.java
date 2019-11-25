package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.Mail;
import fr.enssat.leave_manager.model.PasswordResetToken;
import fr.enssat.leave_manager.repository.PasswordResetTokenRepository;
import fr.enssat.leave_manager.service.EmailService;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private EmailService emailService;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employes")
    public String showEmployees(Model model) {

        logger.debug("GET /employes");

        model.addAttribute("title", "Liste des employés");

        // Get employees
        List<EmployeeEntity> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);

        return "employees";
    }

    @GetMapping("/employe/ajouter")
    public String showAddEmployee(Model model) {

        model.addAttribute("title", "Ajouter un employé");
        return "addEmployee";
    }

    @PostMapping
    public String processSetPasswordForm(EmployeeEntity user,
                                         BindingResult result,
                                         HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addEmployee";
        }
        /*EmployeeEntity user = employeeService.getEmployeeByEmail(form.getEmail());
        if (user == null) {
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "addEmployee";
        }*/

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        System.out.println(token.getToken());
        token.setUser(user);
        System.out.println(token.getUser());
        token.setExpiryDate(30);
        System.out.println(token.getExpiryDate());
        System.out.println(token.isExpired());
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("no-reply@leavemanager.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Demande de réinitialisation de mot de passe");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://leave-manager.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setModel(model);
        //System.out.println(mail.getModel());
        emailService.sendEmail(mail);
        // TODO add une div html pour success voir page login.html
        return "redirect:/employe/ajouter?success";

    }

    @GetMapping("/employe/modifier")
    public String showUpdateEmployee(Model model) {

        model.addAttribute("title", "Modifier l'employé");
        return "updateEmployee";
    }
}
