package zagnitko.messenger.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Main controller. It it used as router.
 * @author zagnitko
 */
@Controller
public class MainController {

    /**
     * Return the main page.
     * @return - hello.jsp
     */
    @RequestMapping(value = { "/", "/welcome**" }, method = RequestMethod.GET)
    public ModelAndView defaultPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("head", "Welcome to the Messenger!");
        model.addObject("description", "You may login or sign up here.");
        model.setViewName("hello");
        return model;
    }

    /**
     * Return admin page.
     * @return - admin.jsp
     */
    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "This is the admin page");
        model.addObject("message", "You can play God here!");
        model.setViewName("admin");
        return model;
    }

    /**
     * Return login page.
     * @param error - authorization error.
     * @param logout - log out.
     * @return - login.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        return model;
    }

    /**
     * 403 error page.
     * @return - 403.jsp
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("403");
        return model;
    }

    /**
     * Registration page.
     * @param error - registration error.
     * @return - register.jsp
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(value = "error", required = false) String error) {
        ModelAndView model = new ModelAndView();
        model.setViewName("register");
        return model;
    }
}
