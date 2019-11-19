package fr.enssat.leave_manager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/login")
    public ModelAndView showLogin() {

        String viewName = "login";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "Page de connexion");

        return new ModelAndView(viewName, model);

    }

    //@Secured({"ROLE_HR","ROLE_HRD"})
    @RequestMapping(value = "/RH/dashboard", method = RequestMethod.GET)
    public ModelAndView showDashboard() {
        /*ModelAndView model = new ModelAndView();
        model.setViewName("dashboard");
        return model*/

        String viewName = "dashboard";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "dashboard");

        return new ModelAndView(viewName, model);
    }


    @GetMapping("/map")
    public ModelAndView showMap() {

        String viewName = "map";
        Map<String, Object> model = new HashMap<>();
        model.put("title", "map");

        return new ModelAndView(viewName, model);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "welcomePage";
    }

    /*@RequestMapping("/**")
    public String handleRequest2(HttpServletRequest request, Model model) {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        model.addAttribute("uri", request.getRequestURI())
                .addAttribute("user", auth.getName())
                .addAttribute("roles", auth.getAuthorities());
        return "tables";
    }*/

    /*@RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            Employee loginedUser = (Employee) ((Authentication) principal).getPrincipal();

            String userInfo = WebUtils.toString(loginedUser);

            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);

        }

        return "403Page";
    }*/

}
