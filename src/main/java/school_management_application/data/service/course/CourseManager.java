package school_management_application.data.service.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school_management_application.data.dao.course.CourseDao;
import school_management_application.data.dao.student.StudentDao;
import school_management_application.data.sequencer.CourseSequencer;
import school_management_application.model.Course;
import school_management_application.model.Student;

import java.time.LocalDate;
import java.util.List;

@Service
public class CourseManager implements CourseService{

    private final CourseDao courseDao;
    private final StudentDao studentDao;

    @Autowired
    public CourseManager(CourseDao courseDao, StudentDao studentDao) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
    }

    @Override
    public Course saveCourse(String courseName, LocalDate startDate, int weekDuration) {
        Course course = new Course(CourseSequencer.nextCourseId(), courseName, startDate, weekDuration);
        return  courseDao.saveCourse(course);
    }

    @Override
    public Course findById(int id) {
        return courseDao.findById(id);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseDao.findByName(name);
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        return courseDao.findByDate(date);
    }

    @Override
    public List<Course> findAll() {
        return courseDao.findAll();
    }

    @Override
    public boolean addStudentToCourse(int courseId, int studentId) {
        Student student = studentDao.findById(studentId);
        Course course = courseDao.findById(courseId);

        return courseDao.addStudentToCourse(course, student);
    }

    @Override
    public boolean removeStudentFromCourse(int courseId, int studentId) {
        Student student = studentDao.findById(studentId);
        Course course = courseDao.findById(courseId);

        return courseDao.removeStudentFromCourse(course, student);
    }

    @Override
    public boolean removeCourse(Course course) {
        for(Student student : studentDao.findAll()){
            student.getCourses().remove(course);
        }

        return courseDao.removeCourse(course);
    }
}

