package fr.enssat.leave_manager.watchlist.controller;

import fr.enssat.leave_manager.watchlist.model.WatchlistEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {
    private List<WatchlistEmployee> watchlistEmployees = new ArrayList<WatchlistEmployee>();

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        String viewName = "watchlist";

        watchlistEmployees.clear();
        watchlistEmployees.add(new WatchlistEmployee("Stark", "Tony", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new WatchlistEmployee("Rogers", "Steve", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new WatchlistEmployee("Banner", "Bruce", "Avengers", "Nick Fury"));
        watchlistEmployees.add(new WatchlistEmployee("Parker", "Peter", "Avengers", "Nick Fury"));


        Map<String, Object> model = new HashMap<String, Object>();
        model.put("watchlistEmployee", watchlistEmployees);
        model.put("numberOfEmployee", watchlistEmployees.size());

        return new ModelAndView(viewName, model);
    }
}
