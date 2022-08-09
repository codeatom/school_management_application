package school_management_application.data.service.student;

import school_management_application.model.Student;

import java.util.List;

public interface StudentService {
    Student saveStudent(String name, String email, String address);
    Student findByEmail(String email);
    List<Student> findByName(String name);
    Student findById(int id);
    List<Student> findAll();
    boolean deleteStudent(Student student);
}
