package school_management_application.data.dao.student;

import school_management_application.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {

    private static List<Student> students = new ArrayList<>();

    public static void setStudents(List<Student> students) {
        StudentDaoList.students = students;
    }

    @Override
    public Student saveStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("student is null");

        students.add(student);
        return student;
    }

    @Override
    public Student findByEmail(String email) {
        if (email == null) throw new IllegalArgumentException("email is null");

        for(Student student : students){
            if (student.getEmail().equalsIgnoreCase(email)){
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        if (name == null) throw new IllegalArgumentException("name is null");

        List<Student> studentList = new ArrayList<>();

        for(Student student : students){
            if (student.getName().toLowerCase().contains(name.toLowerCase())){
                studentList.add(student);
            }
        }

        return studentList;
    }

    @Override
    public Student findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("id is not valid");

        for(Student student : students){
            if (student.getId() == id){
                return student;
            }
        }

        return null;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {
        if (student == null) throw new IllegalArgumentException("student is null");
        students.remove(student);

        return true;
    }
}
