package lnu.asmoderos.webApplication.services;

import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.News;

import java.util.List;

public interface ICourseService {
    void addCourse(Course course);

    Course getCourseByTitle(String title);

    List<String> getLessonsList(int id);

    Course getCourseById(int id);

    List<Course> getCourseList();

    void deleteCourseById(int id);
}
