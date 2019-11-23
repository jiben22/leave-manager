package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RequestToBeProcessedController {

    Logger logger = LoggerFactory.getLogger(RequestToBeProcessedController.class);

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public RequestToBeProcessedController(LeaveRequestServiceImpl leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @GetMapping("/demandes")
    public String showRequestsToBeProcessed(Model model) {

        logger.debug("GET /demandes");

        model.addAttribute("title", "Demandes à traiter");

        // Get pending request to be processed
        List<LeaveRequestEntity> leavesRequests = leaveRequestService.getLeaveRequests();
        model.addAttribute("leavesRequests", leavesRequests);

        return "requestsToBeProcessed";
    }

    @GetMapping("/demande/valider")
    public String showValidateRequestToBeProcessed(Model model) {

        model.addAttribute("title", "Valider la demande à traiter");
        return "validateRequestToBeProcessed";
    }
}
