package school_management_application.data.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school_management_application.data.dao.course.CourseDao;
import school_management_application.data.dao.lecture.LectureDao;
import school_management_application.data.dao.teacher.TeacherDao;
import school_management_application.data.sequencer.TeacherSequencer;
import school_management_application.model.Course;
import school_management_application.model.Lecture;
import school_management_application.model.Teacher;

import java.util.List;

@Service
public class TeacherManager implements TeacherService{

    private final TeacherDao teacherDao;
    private final LectureDao lectureDao;

    private final CourseDao courseDao;

    @Autowired
    public TeacherManager(TeacherDao teacherDao, LectureDao lectureDaoDao, CourseDao courseDao) {
        this.teacherDao = teacherDao;
        this.lectureDao = lectureDaoDao;
        this.courseDao = courseDao;
    }

    @Override
    public Teacher saveTeacher(String name, String email, String address) {
        Teacher teacher = new Teacher(TeacherSequencer.nextTeacherId(), name, email, address);
        return teacherDao.saveTeacher(teacher);
    }

    @Override
    public Teacher findByEmail(String email) {
        return teacherDao.findByEmail(email);
    }

    @Override
    public List<Teacher> findByName(String name) {
        return teacherDao.findByName(name);
    }

    @Override
    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public boolean deleteTeacher(Teacher teacher) {
        for(Lecture lecture : lectureDao.findAll()){
            lecture.getTeachers().remove(teacher);
        }

        for(Course course : courseDao.findAll()){
            if(course.getTeacher().equals(teacher)){
                course.setTeacher(null);
            }
        }

        return teacherDao.deleteTeacher(teacher);
    }
}

