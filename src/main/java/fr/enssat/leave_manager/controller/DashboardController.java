package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.service.impl.StatisticsServiceImpl;
import fr.enssat.leave_manager.utils.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("/")
    public String showDashboard(Model model) {

        model.addAttribute("title", "Vue d'ensemble");
        model.addAttribute("leaveByStatus", statisticsService.getLeaveByStatus());

        return "dashboard";
    }
}
