package fr.enssat.leave_manager.controller;

import fr.enssat.leave_manager.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LeaveManagerController {
    private List<Employee> watchlistEmployees = new ArrayList<Employee>();

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlistEmployee";

        watchlistEmployees.clear();
        /*watchlistEmployees.add(new Employee("Stark", "Tony", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new Employee("Rogers", "Steve", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new Employee("Banner", "Bruce", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new Employee("Parker", "Peter", "Avengers", "Nick Fury"));*/


        Map<String, Object> model = new HashMap<String, Object>();
        model.put("watchlistEmployee", watchlistEmployees);
        model.put("numberOfEmployee", watchlistEmployees.size());

        return new ModelAndView(viewName, model);
    }
}
