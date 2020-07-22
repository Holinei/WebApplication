package lnu.asmoderos.webApplication.dao;

import lnu.asmoderos.webApplication.model.Course;
import lnu.asmoderos.webApplication.model.News;


import java.util.List;

public interface ICourseDAO {
    void saveCourse(Course course);

    Course getCourseByTitle(String title);

    List<String> getLessonsList(int id);

    Course getCourseById(int id);

    List<Course> getCourseList();

    void deleteCourseById(int id);
}
