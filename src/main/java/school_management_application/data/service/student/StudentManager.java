package school_management_application.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school_management_application.data.dao.course.CourseDao;
import school_management_application.data.dao.student.StudentDao;
import school_management_application.data.sequencer.StudentSequencer;
import school_management_application.model.Course;
import school_management_application.model.Student;

import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao  courseDao) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
    }

    @Override
    public Student saveStudent(String name, String email, String address) {
        Student student = new Student(StudentSequencer.nextStudentId(), name, email, address);
        return studentDao.saveStudent(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentDao.findByEmail(email);
    }

    @Override
    public List<Student> findByName(String name) {
        return studentDao.findByName(name);
    }

    @Override
    public Student findById(int id) {
        return studentDao.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public boolean deleteStudent(Student student) {
        for(Course course : courseDao.findAll()){
            course.getStudents().remove(student);
        }

        return studentDao.deleteStudent(student);
    }
}