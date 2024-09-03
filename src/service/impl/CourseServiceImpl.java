package service.impl;

import dao.CourseDAO;
import model.Course;
import model.Course.StudyFormat;
import service.CourseService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDao;

    public CourseServiceImpl(CourseDAO courseDao) {
        this.courseDao = courseDao;
    }

    @Override
    public void addCourse(String name, String description, String durationInput, String formatInput) {
        try {
            // Курстун аты жана описаниесинин бош эмес экенин текшерүү
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Курс аты бош болбошу керек.");
            }
            if (description == null || description.trim().isEmpty()) {
                throw new IllegalArgumentException("Курс описаниеси бош болбошу керек.");
            }

            LocalDate duration = LocalDate.parse(durationInput);
            StudyFormat studyFormat = StudyFormat.valueOf(formatInput.toUpperCase());

            Course course = new Course(name, description, duration, studyFormat);
            courseDao.createCourse(course);
            System.out.println("Курс ийгиликтүү кошулду!");

        } catch (DateTimeParseException e) {
            System.out.println("Ката: Курс узактыгы үчүн туура эмес формат киргизилди. Формат 'ЖЫЛ-АЙ-КҮН' болушу керек.");
        } catch (IllegalArgumentException e) {
            System.out.println("Ката: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Курс кошууда ката кетти: " + e.getMessage());
        }
    }

    @Override
    public Course getCourse(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public Course[] listAllCourses() {
        return courseDao.getAllCourses();
    }

    @Override
    public void updateCourse(Long id, String newName, String newDescription, String newDurationInput, String newFormatInput) {
        Course course = courseDao.getCourseById(id);
        if (course != null) {
            try {
                // Жаңы аталыш жана описаниеси бош эмес экенин текшерүү
                if (newName == null || newName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Жаңы аталыш бош болбошу керек.");
                }
                if (newDescription == null || newDescription.trim().isEmpty()) {
                    throw new IllegalArgumentException("Жаңы описание бош болбошу керек.");
                }

                course.setName(newName);
                course.setDescription(newDescription);
                course.setDuration(LocalDate.parse(newDurationInput));
                course.setStudyFormat(StudyFormat.valueOf(newFormatInput.toUpperCase()));

                courseDao.updateCourse(course);
                System.out.println("Курс ийгиликтүү жаңыртылды!");

            } catch (DateTimeParseException e) {
                System.out.println("Ката: Жаңыртуу үчүн туура эмес дата форматы киргизилди. Формат 'ЖЫЛ-АЙ-КҮН' болушу керек.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ката: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Курс жаңыртууда ката кетти: " + e.getMessage());
            }
        } else {
            System.out.println("Курс табылган жок.");
        }
    }

    @Override
    public void deleteCourse(Long id) {
        courseDao.deleteCourse(id);
    }
}
