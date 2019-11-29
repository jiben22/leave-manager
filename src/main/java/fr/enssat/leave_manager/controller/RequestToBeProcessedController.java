package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class RequestToBeProcessedController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public RequestToBeProcessedController(LeaveRequestServiceImpl leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping("/demandes")
    public String showRequestsToBeProcessed(Model model) {

        log.info("GET /demandes");

        model.addAttribute("title", "Demandes à traiter");

        // Get pending request to be processed
        model.addAttribute("pendingLeaveRequests", leaveRequestService.getLeaveRequestByStatus(LeaveStatus.PENDING));
        model.addAttribute("leavesRequests", leaveRequestService.getLeaveRequests());

        return "requestsToBeProcessed";
    }

    @GetMapping("/demande/valider")
    public String showValidateRequestToBeProcessed(Model model) {

        model.addAttribute("title", "Valider la demande à traiter");
        return "validateRequestToBeProcessed";
    }
}
