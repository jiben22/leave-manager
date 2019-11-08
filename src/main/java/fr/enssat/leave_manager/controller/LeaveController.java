package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LeaveController {

    @GetMapping("/demandesconges")
    public ModelAndView showListLeave() {

        String viewName = "leaves";
        Map<String,Object> model = new HashMap<>();
        model.put("title", "Mes demandes");

        return new ModelAndView(viewName,model);
    }

}
