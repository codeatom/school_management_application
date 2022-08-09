package school_management_application.data.service.course;

import school_management_application.model.Course;

import java.time.LocalDate;
import java.util.List;

public interface
CourseService {
    Course saveCourse(String courseName, LocalDate startDate, int weekDuration);
    Course findById(int id);
    List<Course> findByName(String name);
    List<Course> findByDate(LocalDate date);
    List<Course> findAll();
    boolean addStudentToCourse(int courseId, int studentId);
    boolean removeStudentFromCourse(int courseId, int studentId);
    boolean removeCourse(Course course);
}
