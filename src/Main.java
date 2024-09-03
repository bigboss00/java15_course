import dao.CourseDAO;
import dao.impl.CourseDAOImpl;
import model.Course;
import service.CourseService;
import service.impl.CourseServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CourseDAO courseDao = new CourseDAOImpl();
        CourseService courseService = new CourseServiceImpl(courseDao);

        while (true) {
            System.out.println("Танданыз: 1-Кошуу, 2-Айди аркылуу алуу, 3-Бардык курстар, 4-Жаңыртуу, 5-Өчүрүү, 6-Чыгуу");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 1) {
                System.out.print("Курс аты: ");
                String name = scanner.nextLine();

                System.out.print("Курстун описаниеси: ");
                String description = scanner.nextLine();

                System.out.print("Курс узактыгы (ЖЫЛ-АЙ-КҮН): ");
                String durationInput = scanner.nextLine();

                System.out.print("Окуу форматы (ONLINE же OFFLINE): ");
                String formatInput = scanner.nextLine();

                courseService.addCourse(name, description, durationInput, formatInput);

            } else if (choice == 2) {
                System.out.print("Курс ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                Course course = courseService.getCourse(id);
                if (course != null) {
                    System.out.println("Курс аты: " + course.getName());
                    System.out.println("Курстун описаниеси: " + course.getDescription());
                    System.out.println("Курс узактыгы: " + course.getDuration());
                    System.out.println("Окуу форматы: " + course.getStudyFormat());
                } else {
                    System.out.println("Курс табылган жок.");
                }

            } else if (choice == 3) {
                Course[] courses = courseService.listAllCourses();
                for (Course course : courses) {
                    System.out.println("ID: " + course.getId() + ", Аты: " + course.getName());
                }

            } else if (choice == 4) {
                System.out.print("Курс ID: ");
                Long id = Long.parseLong(scanner.nextLine());

                Course course = courseService.getCourse(id);
                if (course != null) {
                    System.out.print("Жаңы аталышы (эскиси: " + course.getName() + "): ");
                    String newName = scanner.nextLine();

                    System.out.print("Жаңы описание (эскиси: " + course.getDescription() + "): ");
                    String newDescription = scanner.nextLine();

                    System.out.print("Жаңы узактыгы (ЖЫЛ-АЙ-КҮН, эскиси: " + course.getDuration() + "): ");
                    String newDurationInput = scanner.nextLine();

                    System.out.print("Жаңы окуу форматы (эскиси: " + course.getStudyFormat() + "): ");
                    String newFormatInput = scanner.nextLine();

                    courseService.updateCourse(id, newName, newDescription, newDurationInput, newFormatInput);
                } else {
                    System.out.println("Мындай айдидеги курс табылган жок.");
                }

            } else if (choice == 5) {
                System.out.print("Курс ID: ");
                Long id = Long.parseLong(scanner.nextLine());
                courseService.deleteCourse(id);
                System.out.println("Курс ийгиликтүү өчүрүлдү!");

            } else if (choice == 6) {
                System.out.println("Программадан чыгуу");
                break;
            } else {
                System.out.println("1ден 6га чейинки санды гана тандай аласыз, кайталаныз.");
            }
        }
    }
}
