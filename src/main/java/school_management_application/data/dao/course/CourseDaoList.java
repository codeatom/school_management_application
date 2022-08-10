package school_management_application.data.dao.course;

import school_management_application.model.Course;
import school_management_application.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDao{

    private static List<Course> courses = new ArrayList<>();

    public static void setCourses(List<Course> courses) {
        CourseDaoList.courses = courses;
    }

    @Override
    public Course saveCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course is null");

        courses.add(course);
        return course;
    }

    @Override
    public Course findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("is is not valid");

        for(Course course : courses){
            if (course.getId() == id){
                return course;
            }
        }

        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        if (name == null) throw new IllegalArgumentException("name is null");

        List<Course> courseList = new ArrayList<>();

        for(Course course : courses){
            if (course.getCourseName().toLowerCase().contains(name.toLowerCase())){
                courseList.add(course);
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        if (date == null) throw new IllegalArgumentException("date is null");

        List<Course> courseList = new ArrayList<>();

        for(Course course : courses){
            if (course.getStartDate() == date){
                courseList.add(course);
            }
        }

        return courseList;
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean addStudentToCourse(Course course, Student student) {
        if (course == null) throw new IllegalArgumentException("course is null");
        if (student == null) throw new IllegalArgumentException("student is null");

        course.getStudents().add(student);
        student.getCourses().add(course);

        return true;
    }

    @Override
    public boolean removeStudentFromCourse(Course course, Student student) {
        if (course == null) throw new IllegalArgumentException("course is null");
        if (student == null) throw new IllegalArgumentException("student is null");

        course.getStudents().remove(student);
        student.getCourses().remove(course);

        return true;
    }

    @Override
    public boolean removeCourse(Course course) {
        if (course == null) throw new IllegalArgumentException("course is null");
        courses.remove(course);

        return true;
    }
}
