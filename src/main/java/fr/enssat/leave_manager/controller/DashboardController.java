package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.impl.StatisticsServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class DashboardController {
    @Autowired
    private StatisticsServiceImpl statisticsService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String showDashboard(Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("title", "Vue d'ensemble");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && auth.getName() != "anonymousUser" && session.getAttribute("employee") == null) {
            session.setAttribute("employee", employeeService.getEmployeeByEmail(auth.getName()));
        }

        if (request.isUserInRole("ROLE_HR")) {
            model.addAttribute("LeaveByTypes", statisticsService.getLeaveByTypes());
            model.addAttribute("AcceptedLeaveByYear", statisticsService.getAcceptedLeaveByYear());
            model.addAttribute("TreatedLeaveRequestByHR", statisticsService.getTreatedLeaveRequestByHR());
            model.addAttribute("status", Arrays.asList(LeaveStatus.values()));
            model.addAttribute("EffectiveByYear", statisticsService.getEffectiveByYear());
            model.addAttribute("metrics", statisticsService.getMetrics());
        }

        // set calendar
        if (auth.getName() != "anonymousUser") {
            EmployeeEntity employee = employeeService.getEmployeeByEmail(auth.getName());
            model.addAttribute("employeeLeaveRequest", employee.getLeaveRequestList());
        }

        return "dashboard";
    }
}
