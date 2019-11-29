package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.DepartmentEntity;
import fr.enssat.leave_manager.service.DepartmentService;
import fr.enssat.leave_manager.service.impl.DepartmentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentServiceImpl departmentService) {
        this.departmentService = departmentService;
    }

    @PreAuthorize("hasRole('ROLE_HRD')")
    @GetMapping("/departement/ajouter")
    public String showAddDepartmentForm(Model model) {

        log.info("GET /departement/ajouter");

        model.addAttribute("title", "Ajouter un département");
        model.addAttribute("department", new DepartmentEntity());

        return "addDepartmentForm";
    }

    @PreAuthorize("hasRole('ROLE_HRD')")
    @PostMapping("/departement/ajouter")
    public String submitAddDepartmentForm(@Valid @ModelAttribute("department") DepartmentEntity department,
                                          BindingResult result, Model model,
                                          RedirectAttributes redirectAttributes) {

        log.info("POST /departement/ajouter");

        model.addAttribute("title", "Ajouter un département");

        // Check if form has errors
        if (result.hasErrors()) {
            log.info(result.toString());

            // Return form with errors
            return "addDepartmentForm";
        }

        // Save department
        try {
            departmentService.addDepartment(department);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());

            redirectAttributes.addFlashAttribute("message", "Impossible d'enregister le département");
        }

        return "redirect:/equipes";
    }

}
