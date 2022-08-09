package school_management_application.data.service.teacher;

import school_management_application.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(String name, String email, String address);
    Teacher findByEmail(String email);
    List<Teacher> findByName(String name);
    Teacher findById(int id);
    List<Teacher> findAll();
    boolean deleteTeacher(Teacher teacher);
}
