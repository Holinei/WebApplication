package lnu.asmoderos.webApplication.controllers;

import lnu.asmoderos.SessionObject;
import lnu.asmoderos.webApplication.model.Category;
import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.CourseFromPage;
import lnu.asmoderos.webApplication.model.User;
import lnu.asmoderos.webApplication.services.ICategoryService;
import lnu.asmoderos.webApplication.services.ICourseService;
import lnu.asmoderos.webApplication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class CourseController {


    @Autowired
    ICategoryService categoryService;

    @Autowired
    IUserService userService;

    @Autowired
    ICourseService courseService;

    @Resource(name = "sessionObject")
    SessionObject sessionObject;

    @RequestMapping(value = "/courses", method = RequestMethod.GET)
    public String showCourses(Model model, User user) {
        List<Course> list = courseService.getCourseList();
        model.addAttribute("courseList", list);
        if (sessionObject.getRole() == 0) {
            return "admin/courses";
        }
        if (sessionObject.getRole() == 1) {

            return "student/courses";
        }
        if (sessionObject.getRole() == 2) {

            return "user/courses";
        }
        return "courses";
    }

    @RequestMapping(value = "/myCourses", method = RequestMethod.GET)
    public String myCourses(Model model, User user) {
        List<Course> list = courseService.getCourseList();
        List<Integer> listSubscribe = userService.getSubscribeId(sessionObject.getId());
        ArrayList<Course> listSubscribeCourses = new ArrayList<>();
        for (int i = 0; i < listSubscribe.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (listSubscribe.get(i).equals(list.get(j).getCourse_id())) {
                    listSubscribeCourses.add(list.get(j));
                }
            }

        }
        if (listSubscribeCourses.isEmpty()) {
            if (sessionObject.getRole() == 1) {
                return "student/myCoursesEmpty";
            }
            if (sessionObject.getRole() == 2) {
                return "user/myCoursesEmpty";
            }
        }

        if (sessionObject.getRole() == 1) {
            model.addAttribute("courseList", listSubscribeCourses);
            return "student/myCourses";
        }
        if (sessionObject.getRole() == 2) {
            model.addAttribute("courseList", listSubscribeCourses);
            return "user/myCourses";
        }
        model.addAttribute("courseList", list);
        return "courses";
    }

    @RequestMapping(value = "/course-detail", method = RequestMethod.GET)
    public String showCoursePost(@RequestParam(name = "id", required = false) int id, Model model, User user) {
        model.addAttribute("course", courseService.getCourseById(id));
        if (sessionObject.getRole() == 0) {
            return "admin/course-detail";
        }
        if (sessionObject.getRole() == 1) {

            return "student/course-detail";
        }
        if (sessionObject.getRole() == 2) {

            return "user/course-detail";
        }
        return "course-detail";
    }

    @RequestMapping(value = "/course-detail", method = RequestMethod.POST)
    public String postCourseDetail(@RequestParam(name = "id", required = false) int id, Model model, User user) {
        model.addAttribute("course", courseService.getCourseById(id));
        model.addAttribute("courselist", courseService.getLessonsList(id));
        if (sessionObject.getRole() == 0) {
            return "admin/course-detail";
        }
        if (sessionObject.getRole() == 1) {
            return "student/course-detail";
        }
        if (sessionObject.getRole() == 2) {
            return "user/course-detail";
        }
        return "course-detail";
    }

    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public String subscribe(@RequestParam(name = "id", required = false) int id, Model model) {
        if (!sessionObject.isLogged()) {
            model.addAttribute("userModel", new User());
            return "login";
        }
        List<Course> list = courseService.getCourseList();
        List<Integer> listSubscribe = userService.getSubscribeId(sessionObject.getId());
        ArrayList<Course> listSubscribeCourses = new ArrayList<>();
        for (int i = 0; i < listSubscribe.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (listSubscribe.get(i).equals(list.get(j).getCourse_id())) {
                    listSubscribeCourses.add(list.get(j));
                }
            }

        }

        ArrayList<Integer> subscribe = new ArrayList<>();
        subscribe.add(id);
        if (userService.getSubscribeId(sessionObject.getId()).contains(id)) {
            List<Course> list2 = courseService.getCourseList();
            model.addAttribute("courseList", list2);
            return "student/courses";
        }
        User userOld = userService.getUserById(sessionObject.getId());
        User userUpdate = userService.getUserById(sessionObject.getId());

        userOld.setSubscribe(subscribe);
        updateUser(userUpdate, userOld);

        userService.updateUser(userUpdate);
        model.addAttribute("courseList", listSubscribeCourses);
        return "student/myCourses";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(@RequestParam(name = "id", required = false) int id, Model model) {
        if (!sessionObject.isLogged()) {
            model.addAttribute("userModel", new User());
            return "login";
        }
        if (userService.getSubscribeId(sessionObject.getId()).contains(id)) {
            List<Course> list = courseService.getCourseList();
            model.addAttribute("courseList", list);
            return "user/courses";
        }
        model.addAttribute("course", courseService.getCourseById(id));
        return "buy";
    }

    @RequestMapping(value = "/actionBuy", method = RequestMethod.POST)
    public String actionBuy(@RequestParam(name = "id", required = false) int id, Model model) {
        if (!sessionObject.isLogged()) {
            model.addAttribute("userModel", new User());
            return "login";
        }
        ArrayList<Integer> subscribe = new ArrayList<>();
        subscribe.add(id);

        User userOld = userService.getUserById(sessionObject.getId());
        User userUpdate = userService.getUserById(sessionObject.getId());

        userOld.setSubscribe(subscribe);
        updateUser(userUpdate, userOld);

        userService.updateUser(userUpdate);
        List<Course> list = courseService.getCourseList();
        model.addAttribute("courseList", list);
        return "user/courses";
    }

    @RequestMapping(value = "/actionBuy", method = RequestMethod.GET)
    public String actionBuyGET(@RequestParam(name = "id", required = false) int id, Model model) {
        if (!sessionObject.isLogged()) {
            model.addAttribute("userModel", new User());
            return "login";
        }
        List<Course> list = courseService.getCourseList();
        model.addAttribute("courseList", list);
        return "user/courses";
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    public String addCourse(Model model) {

        if (sessionObject.getRole() == 0) {
            model.addAttribute("courseModel", new CourseFromPage());
            List<Category> list = categoryService.getCategoryList();
            model.addAttribute("categories", list);

            return "addCourse";
        }
        return "index";
    }


    @RequestMapping(value = "/deleteCourse", method = RequestMethod.POST)
    public String deleteCourse(@RequestParam(name = "id", required = false) int id, Model model) {

        courseService.deleteCourseById(id);
        List<Course> list = courseService.getCourseList();
        model.addAttribute("courseList", list);
        return "admin/courses";

    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    public String uploadMultipleFiles(@ModelAttribute CourseFromPage courseFromPage, @RequestParam("files") MultipartFile[] files, Model model) {

        if (courseFromPage.getTitle().isEmpty() || courseFromPage.getAuthor().isEmpty() || courseFromPage.getDescription().isEmpty() || files.length == 0 || courseFromPage.getVideo().isEmpty() || courseFromPage.getPrice() == 0) {
            model.addAttribute("courseModel", new CourseFromPage());

            List<Category> list = categoryService.getCategoryList();
            model.addAttribute("categories", list);
            return "addCourse";
        }
        String pathName = "src\\main\\resources\\static\\img\\Courses\\";
        File dir = new File(pathName);
        List<String> listPathToLessons = new ArrayList<>();
        MultipartFile image = files[0];
        try {
            byte[] bytes = image.getBytes();

            if (!dir.exists())
                dir.mkdirs();
            String pathNameFile = dir.getAbsolutePath()
                    + File.separator + image.getOriginalFilename();
            courseFromPage.setImage(image.getOriginalFilename());
            File uploadFile = new File(pathNameFile);
            BufferedOutputStream outputStream = new BufferedOutputStream(
                    new FileOutputStream(uploadFile));
            outputStream.write(bytes);
            outputStream.close();


        } catch (Exception e) {
            e.getMessage();
        }

        for (int i = 1; i < files.length; i++) {

            MultipartFile file = files[i];
            try {
                byte[] bytes = file.getBytes();

                if (!dir.exists())
                    dir.mkdirs();
                String pathNameFile = dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename();

                listPathToLessons.add(file.getOriginalFilename());
                File uploadFile = new File(pathNameFile);
                BufferedOutputStream outputStream = new BufferedOutputStream(
                        new FileOutputStream(uploadFile));
                outputStream.write(bytes);
                outputStream.close();


            } catch (Exception e) {
                e.getMessage();
            }
            courseFromPage.setLessons(listPathToLessons);

        }
        courseService.addCourse(convertCourseFromPage(courseFromPage));
        List<Course> list = courseService.getCourseList();
        model.addAttribute("courseList", list);
        return "admin/courses";
    }

    private Course convertCourseFromPage(CourseFromPage courseFromPage) {
        Course course = new Course();
        course.setAuthor(courseFromPage.getAuthor());
        course.setTitle(courseFromPage.getTitle());
        course.setCourse(courseFromPage.getCourse());
        course.setVideo(courseFromPage.getVideo());
        course.setImage(courseFromPage.getImage());
        course.setDescription(courseFromPage.getDescription());
        course.setLessons(courseFromPage.getLessons());
        Category categoryEnum
                = Category.valueOf(courseFromPage.getCategoryString());
        course.setCategories(Collections.singleton(categoryEnum));
        course.setPrice(courseFromPage.getPrice());
        return course;
    }

    private void updateUser(User newUser, User oldUser) {
        newUser.setName(oldUser.getName());
        newUser.setSurname(oldUser.getSurname());
        newUser.setSubscribe(oldUser.getSubscribe());
        newUser.setLegitka(oldUser.getLegitka());
        newUser.setLastname(oldUser.getLastname());
        newUser.setGroups(oldUser.getGroups());
        newUser.setRoles(oldUser.getRoles());
        newUser.setPassword(oldUser.getPassword());
        newUser.setEmail(oldUser.getEmail());
        newUser.setKurs(oldUser.getKurs());
        newUser.setId(oldUser.getId());

    }
}
