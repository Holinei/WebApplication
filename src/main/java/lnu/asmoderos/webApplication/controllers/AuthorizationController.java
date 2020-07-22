package lnu.asmoderos.webApplication.controllers;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.model.News;
import lnu.asmoderos.webApplication.model.Role;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.IAuthenticationService;
import lnu.asmoderos.webApplication.services.INewsService;
import lnu.asmoderos.webApplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class AuthorizationController {

    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    INewsService newsService;
    @Autowired
    IUserService userService;

    @Resource(name = "sessionObject")
    SessionObject sessionObject;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        sessionObject.setLogged(false);
        sessionObject.setRole(3);
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public String authenticateUser(@ModelAttribute("userModel") User user, Model model) {
        boolean authResult = this.authenticationService.authenticateUser(user);
        int authRoleResult = this.authenticationService.authenticateRole(user);
        if (user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            model.addAttribute("userModel", new User());
            return "login";
        }
        if (authResult) {
            sessionObject.setLogged(true);
            switch (authRoleResult) {
                case 0:
                    sessionObject.setRole(0);
                    return "admin";
                case 1:
                    sessionObject.setRole(1);
                    sessionObject.setId(userService.getIdByEmail(user.getEmail()));
                    return "indexForStudents";
                default:
                    sessionObject.setRole(2);
                    sessionObject.setId(userService.getIdByEmail(user.getEmail()));
                    return "indexForUsers";
            }
        } else {
            model.addAttribute("userModel", new User());
            return "login";
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showMain(@ModelAttribute("userModel") User user, Model model) {
        if (sessionObject.isLogged() && sessionObject.getRole() == 1) {
            List<News> list = newsService.getNewsList();
            model.addAttribute("newsList", list);
            return "indexForStudents";
        }
        if (sessionObject.isLogged() && sessionObject.getRole() == 0) {
            List<News> list = newsService.getNewsList();
            model.addAttribute("newsList", list);
            return "admin";
        }
        if (sessionObject.isLogged() && sessionObject.getRole() == 2) {
            List<News> list = newsService.getNewsList();
            model.addAttribute("newsList", list);
            return "indexForUsers";
        }
        List<News> list = newsService.getNewsList();
        model.addAttribute("newsList", list);
        return "index";
    }

}
