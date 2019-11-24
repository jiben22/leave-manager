package fr.enssat.leave_manager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @GetMapping("/connexion")
    public String showLogin(Model model) {

        model.addAttribute("title", "Page de connexion");
        return "login";

    }

    @GetMapping("/deconnexion")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/connexion";
    }

    @GetMapping("/demande-mot-de-passe")
    public String showForgotPassword(Model model) {

        model.addAttribute("title", "Demande de mot de passe");
        return "forgotPassword";
    }
}
