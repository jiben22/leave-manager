package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.EmployeeEntity;
import fr.enssat.leave_manager.model.LeaveRequestEntity;
import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.service.EmployeeService;
import fr.enssat.leave_manager.service.LeaveRequestService;
import fr.enssat.leave_manager.service.TypeOfLeaveService;
import fr.enssat.leave_manager.service.impl.LeaveRequestServiceImpl;
import fr.enssat.leave_manager.service.impl.TypeOfLeaveServiceImpl;
import fr.enssat.leave_manager.utils.enums.LeaveStatus;
import lombok.extern.slf4j.Slf4j;
import fr.enssat.leave_manager.service.impl.EmployeeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@Slf4j
public class LeaveRequestController {

    private LeaveRequestService leaveRequestService;

    private final EmployeeService employeeService;

    private final TypeOfLeaveService typeOfLeaveService;

    public LeaveRequestController(LeaveRequestServiceImpl leaveRequestService,
                                  EmployeeServiceImpl employeeService,
                                  TypeOfLeaveServiceImpl typeOfLeaveService) {
        this.leaveRequestService = leaveRequestService;
        this.employeeService = employeeService;
        this.typeOfLeaveService = typeOfLeaveService;
    }

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
    public String showAddLeavesRequest(HttpSession session, Model model) {

        log.info("GET /demande-conges/ajouter");

        model.addAttribute("title", "Ajouter une demande de congés");
        model.addAttribute("leaveRequest", new LeaveRequestEntity());

        // Get types of leaves
        List<TypeOfLeaveEntity> typeOfLeaveList =
                typeOfLeaveService.getAllTypeofLeaves();
        model.addAttribute("typesOfLeaves", typeOfLeaveList);

        // Get remaining leave of this employee
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        model.addAttribute("remainingLeave", employee.getRemainingLeave());

        return "addLeavesRequest";
    }

    @PostMapping("/demande-conges/ajouter")
    public String submitAddLeavesRequest(@Valid @ModelAttribute("leaveRequest") LeaveRequestEntity leaveRequest,
                                         @RequestParam("startingDate") String startingDate,
                                         @RequestParam("endingDate") String endingDate,
                                         BindingResult result,
                                         HttpSession session, Model model,
                                         RedirectAttributes redirectAttributes) {

        log.info("POST /demande-conges/ajouter");

        model.addAttribute("title", "Ajouter une demande de congés");

        // Get types of leaves
        List<TypeOfLeaveEntity> typeOfLeaveList =
                typeOfLeaveService.getAllTypeofLeaves();
        model.addAttribute("typesOfLeaves", typeOfLeaveList);

        // Get remaining leave of this employee
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        model.addAttribute("remainingLeave", employee.getRemainingLeave());

        // Convert string date to LocalDateTime
        leaveRequest.setStartingDate( LocalDateTime.parse(startingDate) );
        leaveRequest.setStartingDate( LocalDateTime.parse(endingDate) );

        // Set employee of leave request
        leaveRequest.setEmployee(employee);
        // Set status of leave request
        leaveRequest.setStatus(LeaveStatus.PENDING);

        // Save leave request
        try {
            leaveRequestService.addLeaveRequest(leaveRequest);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());

            redirectAttributes.addAttribute("message", "Impossible d'enregistrer la demande de congés");
            return "redirect:/demandes-conges";
        }

        return "redirect:/demande-conges/" + leaveRequest.getLrid();
    }

    @GetMapping("/demande-conges/modifier/{lrid}")
    public String showUpdateLeavesRequest(@PathVariable String lrid, Model model, HttpSession session) {

        model.addAttribute("title", "Modifier une demande de congés");

        // Get types of leaves
        List<TypeOfLeaveEntity> typeOfLeaveList =
                typeOfLeaveService.getAllTypeofLeaves();
        model.addAttribute("typesOfLeaves", typeOfLeaveList);

        // Get remaining leave of this employee
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        model.addAttribute("remainingLeave", employee.getRemainingLeave());

        // Get leave request
        LeaveRequestEntity leaveRequest =
                leaveRequestService.getLeaveRequest(lrid);
        model.addAttribute("leaveRequest", leaveRequest);

        return "updateLeavesRequest";
    }

    @GetMapping("/demande-conges/{lrid}")
    public String showLeavesRequest(@PathVariable String lrid, Model model, HttpSession session) {

        model.addAttribute("title", "Visualiser la demande de congés");

        // Get leave request
        LeaveRequestEntity leaveRequest =
                leaveRequestService.getLeaveRequest(lrid);
        model.addAttribute("leaveRequest", leaveRequest);

        // Get remaining leave of this employee
        EmployeeEntity employee = (EmployeeEntity) session.getAttribute("employee");
        model.addAttribute("remainingLeave", employee.getRemainingLeave());

        // Add type of leave
        List<TypeOfLeaveEntity> typesOfLeaves = new ArrayList<>();
        typesOfLeaves.add(leaveRequest.getTypeOfLeave());
        model.addAttribute("typesOfLeaves", typesOfLeaves);

        return "showLeavesRequest";
    }

    @GetMapping("/demande-conges/supprimer/{lrid}")
    public String deleteLeaveRequest(@PathVariable String lrid) {
        //leaveRequestService.deleteLeaveRequest(lrid);
        return "redirect:/demande-conges";
    }
}
