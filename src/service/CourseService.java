package service;

import model.Course;

public interface CourseService {
    void addCourse(String name, String description, String durationInput, String formatInput);
    Course getCourse(Long id);
    Course[] listAllCourses();
    void updateCourse(Long id, String newName, String newDescription, String newDurationInput, String newFormatInput);
    void deleteCourse(Long id);
}
