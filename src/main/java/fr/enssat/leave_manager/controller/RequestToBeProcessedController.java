package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class RequestToBeProcessedController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public RequestToBeProcessedController(LeaveRequestServiceImpl leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @GetMapping("/demandes")
    public String showRequestsToBeProcessed(Model model) {

        log.info("GET /demandes");

        model.addAttribute("title", "Demandes à traiter");

        // Get pending request to be processed
        model.addAttribute("pendingLeaveRequests", leaveRequestService.getLeaveRequestByStatus(LeaveStatus.PENDING));
        model.addAttribute("leavesRequests", leaveRequestService.getLeaveRequests());

        return "requestsToBeProcessed";
    }

    @GetMapping("/demande/valider/{lrid}")
    public String showValidateRequestToBeProcessed(@PathVariable String lrid, Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        EmployeeEntity user = (EmployeeEntity) session.getAttribute("employee");
        if (user.getTeamList().contains(lrid) || request.isUserInRole("ROLE_TEAMLEADER") || request.isUserInRole("ROLE_HR")) {

            model.addAttribute("title", "Valider la demande à traiter");
            model.addAttribute("leaveRequest", leaveRequestService.getLeaveRequest(lrid));
            return "validateRequestToBeProcessed";
        }
        response.setStatus(403);
        return "error";
    }
}
