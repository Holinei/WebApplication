package lnu.asmoderos.webApplication.controllers;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.model.*;
import lnu.asmoderos.webApplication.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Controller
public class ApplicationController {

    @Autowired
    INewsService newsService;

    @Autowired
    ICourseService courseService;

    @Autowired
    IUserService userService;

    @Autowired
    ICategoryService categoryService;

    @Resource(name = "sessionObject")
    SessionObject sessionObject;


    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String showCoursePost(Model model, User user) {
        if (sessionObject.getRole() == 0) {
            return "admin/about";
        }
        if (sessionObject.getRole() == 1) {
            return "student/about";
        }
        if (sessionObject.getRole() == 2) {
            return "user/about";
        }
        return "about";
    }

    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public String showTeachers(Model model, User user) {
        if (sessionObject.getRole() == 0) {
            return "admin/teachers";
        }
        if (sessionObject.getRole() == 1) {
            return "student/teachers";
        }
        if (sessionObject.getRole() == 2) {
            return "user/teachers";
        }
        return "teachers";
    }


}
