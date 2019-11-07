package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EmployeeController {

    @GetMapping("/employee")
    public ModelAndView showEmployees() {

        String viewName = "employee";
        Map<String,Object> model = new HashMap<>();

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/employes");

        return new ModelAndView(viewName, model);
    }
}
