package school_management_application.data.dao.teacher;

import school_management_application.model.Teacher;

import java.util.List;

public interface TeacherDao {
    Teacher saveTeacher(Teacher teacher);
    Teacher findByEmail(String email);
    List<Teacher> findByName(String name);
    Teacher findById(int id);
    List<Teacher> findAll();
    boolean deleteTeacher(Teacher teacher);
}
