package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.service.impl.StatisticsServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class DashboardController {

    @Autowired
    private StatisticsServiceImpl statisticsService;

    @GetMapping("/")
    public String showDashboard(Model model, HttpServletRequest request) {

        model.addAttribute("title", "Vue d'ensemble");

        if (request.isUserInRole("ROLE_HR")) {
            model.addAttribute("LeaveByTypes", statisticsService.getLeaveByTypes());
            model.addAttribute("AcceptedLeaveByYear", statisticsService.getAcceptedLeaveByYear());
            model.addAttribute("TreatedLeaveRequestByHR", statisticsService.getTreatedLeaveRequestByHR());
            model.addAttribute("status", Arrays.asList(LeaveStatus.values()));
            model.addAttribute("EffectiveByYear", statisticsService.getEffectiveByYear());
            model.addAttribute("metrics", statisticsService.getMetrics());
        }

        return "dashboard";
    }
}
