package school_management_application.data.dao.course;

import school_management_application.model.Course;
import school_management_application.model.Student;

import java.time.LocalDate;
import java.util.List;

public interface CourseDao {
    Course saveCourse(Course course);
    Course findById(int id);
    List<Course> findByName(String name);
    List<Course> findByDate(LocalDate date);
    List<Course> findAll();
    boolean addStudentToCourse(Course courseId, Student student);
    boolean removeStudentFromCourse(Course courseId, Student student);
    boolean removeCourse(Course course);
}