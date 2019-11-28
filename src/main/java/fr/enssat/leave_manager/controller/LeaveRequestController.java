package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestServiceImpl leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping("/demandes-conges")
    public String showLeavesRequests(Model model) {

        log.info("GET /demandes-conges");

        model.addAttribute("title", "Mes demandes de congés");

        // Get leaves requests
        List<LeaveRequestEntity> leavesRequests = leaveRequestService.getLeaveRequests();
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
