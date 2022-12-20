package school_management_application.data.dao.teacher;

import school_management_application.model.Teacher;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class TeacherDaoList implements TeacherDao{

    private static List<Teacher> teachers = new ArrayList<>();

    public static void setTeachers(List<Teacher> teachers) {
        TeacherDaoList.teachers = teachers;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        if (teacher == null) throw new IllegalArgumentException("teacher is null");

        teachers.add(teacher);
        return teacher;
    }

    @Override
    public Teacher findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email is null");

        for(Teacher teacher : teachers){
            if (teacher.getEmail().equalsIgnoreCase(email)){
                return teacher;
            }
        }

        return null;
    }

    @Override
    public List<Teacher> findByName(String name) {
        if (name == null) throw new IllegalArgumentException("name is null");

    //  return teachers.stream().filter(t -> t.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());

        List<Teacher> teacherList = new ArrayList<>();

        for(Teacher teacher : teachers){
            if (teacher.getName().toLowerCase().contains(name.toLowerCase())){
                teacherList.add(teacher);
            }
        }

        return teacherList;
    }

    @Override
    public Teacher findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not valid");

        for(Teacher teacher : teachers){
            if (teacher.getId() == id){
                return teacher;
            }
        }

        return null;
    }

    @Override
    public List<Teacher> findAll() {
        return teachers;
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        if (teacher == null) throw new IllegalArgumentException("teacher is null");
        teachers.remove(teacher);

        return true;
    }
}
