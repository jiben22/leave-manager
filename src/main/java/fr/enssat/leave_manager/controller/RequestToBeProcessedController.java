package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestToBeProcessedController {

    @GetMapping("/demandes")
    public String showRequestsToBeProcessed(Model model) {

        model.addAttribute("title", "Demandes à traiter");
        return "requestsToBeProcessed";
    }

    @GetMapping("/demande/valider")
    public String showValidateRequestToBeProcessed(Model model) {

        model.addAttribute("title", "Valider la demande à traiter");
        return "validateRequestToBeProcessed";
    }
}
