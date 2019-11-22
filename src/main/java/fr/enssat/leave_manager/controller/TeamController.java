package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TeamController {

    @GetMapping("/equipes")
    public ModelAndView showEmployees() {

        String viewName = "teams";
        Map<String,Object> model = new HashMap<>();
        model.put("title", "Liste des équipes");

        return new ModelAndView(viewName, model);
    }
}
