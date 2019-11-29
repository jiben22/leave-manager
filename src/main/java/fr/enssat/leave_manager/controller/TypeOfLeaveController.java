package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.TypeOfLeaveEntity;
import fr.enssat.leave_manager.service.TypeOfLeaveService;
import fr.enssat.leave_manager.service.impl.TypeOfLeaveServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
public class TypeOfLeaveController {

    private final TypeOfLeaveService typeOfLeaveService;

    @Autowired
    public TypeOfLeaveController(TypeOfLeaveServiceImpl typeOfLeaveService) {
        this.typeOfLeaveService = typeOfLeaveService;
    }

    @GetMapping("/types-conges")
    public String showTypesOfLeaves(Model model) {

        log.info("GET /types-conges");
        model.addAttribute("title", "Liste des types de congés");

        // Get types of leaves
        List<TypeOfLeaveEntity> typeOfLeaves = typeOfLeaveService.getAllTypeofLeaves();
        model.addAttribute("typesOfLeaves", typeOfLeaves);
        return "typesOfLeaves";
    }

    @GetMapping("/type-conges/ajouter")
    public String showAddTypeOfLeaves(Model model) {

        model.addAttribute("title", "Ajouter un type de congés");
        model.addAttribute("typeOfLeave", new TypeOfLeaveEntity());

        return "addTypeOfLeaves";
    }

    @PostMapping("/type-conges/ajouter")
    public String submitAddTypeOfLeaveForm(@Valid @ModelAttribute ("typeOfLeave") TypeOfLeaveEntity typeOfLeave,
                                           BindingResult result,  ModelMap model,
                                           RedirectAttributes redirectAttributes) {

        log.info("submitAddTypeOfLeaveForm() : {}", typeOfLeave);

        model.addAttribute("title", "Ajouter un type de congés");

        if (result.hasErrors()) {
            log.info(result.toString());

            return "addTypeOfLeaves";
        }

        // Save the new type of leaves
        try {
            typeOfLeaveService.addTypeOfLeave(typeOfLeave);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());

            redirectAttributes.addFlashAttribute("message", "Impossible d'enregister le type de congés");
        }

        return "redirect:/types-conges";
    }

    @GetMapping("/type-conges/archive/{id}")
    public String archive(@PathVariable String id) {
        typeOfLeaveService.deleteTypeOfLeave(id);
        return "redirect:/types-conges";
    }

    @GetMapping("/type-conges/unarchive/{id}")
    public String unarchive(@PathVariable String id) {
        TypeOfLeaveEntity typeOfLeave = typeOfLeaveService.getTypeOfLeave(id);
        typeOfLeave.setIsArchived(false);
        typeOfLeaveService.editTypeOfLeave(typeOfLeave);
        return null;
    }
}
