package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LeaveRequestController {

    @GetMapping("/demandes-conges")
    public ModelAndView showListLeave() {

        String viewName = "leavesRequests";
        Map<String,Object> model = new HashMap<>();
        model.put("title", "Mes demandes de congés");

        return new ModelAndView(viewName,model);
    }

}
