package lnu.asmoderos.webApplication.services.impl;

import lnu.asmoderos.webApplication.dao.ICourseDAO;
import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.News;
import lnu.asmoderos.webApplication.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CourseServiceImpl implements ICourseService {

    @Autowired
    ICourseDAO courseDAO;

    public CourseServiceImpl(ICourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }


    @Override
    public void addCourse(Course course) {
        Course courseFromDb = this.courseDAO.getCourseByTitle(course.getTitle());
        if (courseFromDb == null) {
            this.courseDAO.saveCourse(course);
        }
    }

    @Override
    public Course getCourseByTitle(String title) {
        return this.courseDAO.getCourseByTitle(title);
    }

    @Override
    public List<String> getLessonsList(int id) {
        return courseDAO.getLessonsList(id);
    }

    @Override
    public Course getCourseById(int id) {
        return this.courseDAO.getCourseById(id);
    }

    @Override
    public List<Course> getCourseList() {
        return courseDAO.getCourseList();
    }

    @Override
    public void deleteCourseById(int id) {
        courseDAO.deleteCourseById(id);
    }

}
