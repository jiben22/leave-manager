package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/connexion")
    public ModelAndView showLogin() {

        String viewName = "login";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Connexion");

        return new ModelAndView(viewName, model);
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        String viewName = "/error/access-denied";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Accès refusé");

        return new ModelAndView(viewName, model);
    }


    @GetMapping("/verrouillage")
    public ModelAndView showLock() {

        String viewName = "lock";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Verrouillage");

        return new ModelAndView(viewName, model);
    }

    @GetMapping("/demande-mot-de-passe")
    public ModelAndView showForgotPassword() {

        String viewName = "forgotPassword";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Demande de mot de passe");

        return new ModelAndView(viewName, model);
    }
}
