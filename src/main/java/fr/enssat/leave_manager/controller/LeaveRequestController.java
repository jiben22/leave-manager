package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class LeaveRequestController {

    Logger logger = LoggerFactory.getLogger(LeaveRequestController.class);

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/demandes-conges")
    public String showLeavesRequests(Model model, HttpSession session) {
        EmployeeEntity session_employee = (EmployeeEntity) session.getAttribute("employee");
        EmployeeEntity employee = employeeService.getEmployee(session_employee.getEid());

        logger.debug("GET /demandes-conges");

        model.addAttribute("title", "Mes demandes de congés");

        // Get leaves requests
        Set<LeaveRequestEntity> leavesRequests = employee.getLeaveRequestList();
        model.addAttribute("leavesRequests", leavesRequests);

        return "leavesRequests";
    }

    @GetMapping("/demande-conges/ajouter")
    public String showAddLeavesRequest(Model model) {

        model.addAttribute("title", "Ajouter une demande de congés");
        return "addLeavesRequest";
    }

    @GetMapping("/demande-conges/modifier")
    public String showUpdateLeavesRequest(Model model) {

        model.addAttribute("title", "Modifier une demande de congés");
        return "updateLeavesRequest";
    }
}
