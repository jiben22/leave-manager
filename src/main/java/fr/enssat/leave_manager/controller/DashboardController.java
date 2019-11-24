package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.utils.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String showDashboard(Model model) {

        //MailSender.send("test", "Ca marche");

        model.addAttribute("title", "Vue d'ensemble");
        return "dashboard";
    }
}
