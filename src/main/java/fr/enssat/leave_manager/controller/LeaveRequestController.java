package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import lombok.extern.slf4j.Slf4j;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
@Slf4j
public class LeaveRequestController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @GetMapping("/demandes-conges")
    public String showLeavesRequests(Model model, HttpSession session) {
        EmployeeEntity session_employee = (EmployeeEntity) session.getAttribute("employee");
        EmployeeEntity employee = employeeService.getEmployee(session_employee.getEid());

        log.info("GET /demandes-conges");

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

    @GetMapping("/demande-conges/supprimer/{lrid}")
    public String deleteLeaveRequest(@PathVariable String lrid) {
        //leaveRequestService.deleteLeaveRequest(lrid);
        return "redirect:/demande-conges";
    }
}
