package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/")
    public ModelAndView showDashboard() {

        String viewName = "dashboard";
        Map<String,Object> model = new HashMap<>();

        return new ModelAndView(viewName, model);
    }
}
