package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.impl.HRServiceImpl;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class RequestToBeProcessedController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    private HRServiceImpl hrService;

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

    @PreAuthorize("hasRole('ROLE_HR')")
    @GetMapping("/demande/traiter/{lrid}")
    public String showTreatRequestToBeProcessed(@PathVariable String lrid, Model model) {

        model.addAttribute("title", "Traiter la demande");
        model.addAttribute("leaveRequest", leaveRequestService.getLeaveRequest(lrid));
        return "validateRequestToBeProcessed";
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @GetMapping("/demande/valider/{lrid}")
    public String showValidateRequestToBeProcessed(@PathVariable String lrid, @RequestParam String comment, Model model, HttpSession session) {
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        LeaveRequestEntity leaveRequest = leaveRequestService.getLeaveRequest(lrid);
        leaveRequest.setHrComment(comment);
        leaveRequest.setHr(hrService.getHR(employee.getEid()));
        leaveRequestService.acceptLeaveRequest(leaveRequest);
        return "redirect:/demandes";
    }

    @PreAuthorize("hasRole('ROLE_HR')")
    @GetMapping("/demande/refuser/{lrid}")
    public String showDeclineRequestToBeProcessed(@PathVariable String lrid, @RequestParam String comment, Model model, HttpSession session) {
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        LeaveRequestEntity leaveRequest = leaveRequestService.getLeaveRequest(lrid);
        leaveRequest.setHrComment(comment);
        leaveRequest.setHr(hrService.getHR(employee.getEid()));
        leaveRequestService.declineLeaveRequest(leaveRequest);
        return "redirect:/demandes";
    }
}
