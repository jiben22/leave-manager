package fr.enssat.leave_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        String viewName = "dashboard";
        Map<String,Object> model = new HashMap<>();

        RedirectView redirect = new RedirectView();
        redirect.setUrl("/");

        return new ModelAndView(viewName, model);
    }
}
