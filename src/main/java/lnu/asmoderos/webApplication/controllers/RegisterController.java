package lnu.asmoderos.webApplication.controllers;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.model.Group;
import lnu.asmoderos.webApplication.model.RegisterUser;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.IGroupService;
import lnu.asmoderos.webApplication.services.IRegisterService;
import lnu.asmoderos.webApplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import lnu.asmoderos.webApplication.model.Role;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    IUserService userService;
    @Autowired
    IRegisterService registerService;
    @Autowired
    IGroupService groupService;
    @Resource(name = "sessionObject")
    SessionObject sessionObject;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerFormUser(Model model) {
        model.addAttribute("userModel", new RegisterUser());
        return "registerUser";
    }


    @RequestMapping(value = "/registerStudent", method = RequestMethod.GET)
    public String registerForm(Model model) {
        if (sessionObject.getRole() == 0) {
            model.addAttribute("userModel", new RegisterUser());
            List<Group> list = groupService.getGroupList();
            model.addAttribute("groups", list);
            return "registerStudent";
        }
        if (sessionObject.getRole() == 1) {
            return "indexForStudents";
        }
        model.addAttribute("userModel", new RegisterUser());
        return "registerUser";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterUser registerUser, Model model) throws InterruptedException {

        if (registerUser.getSurname().isEmpty() || registerUser.getName().isEmpty() || registerUser.getLastname().isEmpty() || registerUser.getEmail().isEmpty() || registerUser.getPassword().isEmpty() || registerUser.getRepeatPassword().isEmpty()) {
            model.addAttribute("userModel", new RegisterUser());

            List<Group> list = groupService.getGroupList();
            model.addAttribute("groups", list);
            return "registerStudent";
        }
        if (!(registerService.checkUser(convertRegisterUserToUser(registerUser)))) {
            model.addAttribute("userModel", new RegisterUser());

            return "registerStudent";
        }

        if (registerUser.getPassword().equals(registerUser.getRepeatPassword())) {

            this.userService.registerUser(convertRegisterUserToUser(registerUser));
            model.addAttribute("userModel", new RegisterUser());
            return "login";
        } else {
            model.addAttribute("userModel", new RegisterUser());
            return "registerStudent";
        }

    }

    @RequestMapping(value = "/registerStudent", method = RequestMethod.POST)
    public String registerStudent(@ModelAttribute RegisterUser registerStudent, Model model) throws InterruptedException {
        if (registerStudent.getSurname().isEmpty() || registerStudent.getName().isEmpty() || registerStudent.getLastname().isEmpty() || registerStudent.getEmail().isEmpty() || registerStudent.getPassword().isEmpty() || registerStudent.getRepeatPassword().isEmpty() || registerStudent.getLegitka().isEmpty()) {
            model.addAttribute("userModel", new RegisterUser());

            List<Group> list = groupService.getGroupList();
            model.addAttribute("groups", list);
            return "registerStudent";
        }
        if (!(registerService.checkUser(convertRegisterUserToStudent(registerStudent)))) {
            model.addAttribute("userModel", new RegisterUser());
            return "registerStudent";
        }
        this.userService.registerUser(convertRegisterUserToStudent(registerStudent));
        model.addAttribute("userModel", new RegisterUser());
        return "login";
    }

    private User convertRegisterUserToUser(RegisterUser registerUser) {
        User user = new User();
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());
        user.setName(registerUser.getName());
        user.setSurname(registerUser.getSurname());
        user.setLastname(registerUser.getLastname());
        HashSet<Role> newRole = new HashSet<Role>();
        newRole.add(Role.OTHER);
        user.setRoles(newRole);
        return user;
    }

    private User convertRegisterUserToStudent(RegisterUser registerStudent) {
        User user = new User();
        user.setEmail(registerStudent.getEmail());
        user.setPassword(registerStudent.getPassword());
        user.setName(registerStudent.getName());
        user.setSurname(registerStudent.getSurname());
        user.setLastname(registerStudent.getLastname());
        user.setLegitka(registerStudent.getLegitka());
        user.setKurs(registerStudent.getKurs());
        HashSet<Role> newRole = new HashSet<Role>();
        newRole.add(Role.STUDENT);
        user.setRoles(newRole);
        Group groupEnum
                = Group.valueOf(registerStudent.getGroupString());
        user.setGroups(Collections.singleton(groupEnum));
        return user;
    }

}
