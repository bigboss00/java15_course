package dao;

import model.Course;

public interface CourseDAO {
    void createCourse(Course course);
    Course getCourseById(Long id);
    Course[] getAllCourses();
    void updateCourse(Course course);
    void deleteCourse(Long id);
}



