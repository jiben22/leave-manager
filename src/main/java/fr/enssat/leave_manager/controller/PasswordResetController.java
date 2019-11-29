package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.PasswordResetDto;
import fr.enssat.leave_manager.model.PasswordResetToken;
import fr.enssat.leave_manager.repository.PasswordResetTokenRepository;
import fr.enssat.leave_manager.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/resetPassword/{option}")
public class PasswordResetController {

    @Autowired
    private EmployeeService userService;
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("passwordResetForm")
    public PasswordResetDto passwordReset() {
        return new PasswordResetDto();
    }
    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
                                           Model model, @PathVariable String option) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token);
        if (resetToken == null) {
            model.addAttribute("error", "Token invalide.");
        } else if (resetToken.isExpired()) {
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        if(option.equals("new")) {
            model.addAttribute("title", "Création de mot de passe");
            model.addAttribute("description", "Choisissez votre mot de passe");
            model.addAttribute("submit", "CRÉER MON MOT DE PASSE");
            model.addAttribute("option", "new");

        } else {
            model.addAttribute("title", "Réinitialisation de mot de passe");
            model.addAttribute("description", "Entrez votre nouveau mot de passe");
            model.addAttribute("submit", "RÉINITIALISER LE MOT DE PASSE");
            model.addAttribute("option", "pwd");


        }
        return "resetPassword";
    }

    @PostMapping
    public String handlePasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes, @PathVariable String option) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetForm", result);
            redirectAttributes.addFlashAttribute("passwordResetForm", form);
            return "redirect:/resetPassword/"+option+"?token=" + form.getToken();
        }
        log.info("coucou");

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        EmployeeEntity user = token.getUser();

        System.out.println("pwd" + form.getPassword());
        user.setPassword(form.getPassword());
        userService.editEmployee(user);
        tokenRepository.delete(token);

        return "redirect:/connexion?resetSuccess";
    }

}
