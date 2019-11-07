package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LeaveListController {

    @GetMapping("/demandesconges")
    public ModelAndView showListLeave() {

        String viewName = "listLeave";

        Map<String,Object> model = new HashMap<>();


        return new ModelAndView(viewName,model);
    }

}
