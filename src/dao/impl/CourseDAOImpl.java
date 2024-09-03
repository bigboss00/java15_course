package dao.impl;

import dao.CourseDAO;
import model.Course;

import java.util.Arrays;

public class CourseDAOImpl implements CourseDAO {
    private Course[] courses = new Course[10];
    private int size = 0;
//    private Long idCounter = 1L; // Курс IDлерин автоматтык түрдө көбөйтүү

    @Override
    public void createCourse(Course course) {
        if (size >= courses.length) {
            courses = Arrays.copyOf(courses, courses.length * 2); // Массивди кеңейтүү
        }
//        course.setId(idCounter++);
        courses[size++] = course;
    }

    @Override
    public Course getCourseById(Long id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId().equals(id)) {
                return courses[i];
            }
        }
        return null;
    }

    @Override
    public Course[] getAllCourses() {
        return Arrays.copyOf(courses, size);
    }

    @Override
    public void updateCourse(Course course) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId().equals(course.getId())) {
                courses[i] = course;
                break;
            }
        }
    }

    @Override
    public void deleteCourse(Long id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId().equals(id)) {
                courses[i] = courses[size - 1];
                courses[size - 1] = null;
                size--;
                break;
            }
        }
    }
}
