package school_management_application.maintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school_management_application.data.dao.course.CourseDao;
import school_management_application.data.dao.lecture.LectureDao;
import school_management_application.data.dao.student.StudentDao;
import school_management_application.data.dao.teacher.TeacherDao;
import school_management_application.model.Course;
import school_management_application.model.Lecture;
import school_management_application.model.Student;
import school_management_application.model.Teacher;

import javax.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static school_management_application.maintenance.StaticResources.*;

@Component
public class ApplicationTerminator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTerminator.class);

    private final CourseDao courseDao;
    private final StudentDao studentDao;
    private final TeacherDao teacherDao;
    private final LectureDao lectureDao;
    private final ObjectMapper objectMapper;

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    public ApplicationTerminator(CourseDao courseDao, StudentDao studentDao,
                                 TeacherDao teacherDao, LectureDao lectureDao, ObjectMapper objectMapper) {
        this.courseDao = courseDao;
        this.studentDao = studentDao;
        this.teacherDao = teacherDao;
        this.lectureDao = lectureDao;
        this.objectMapper = objectMapper;
    }

    @PreDestroy
    public void destroy(){
        if(!activeProfile.equals("test")){
            List<Student> existingStudents = studentDao.findAll();
            List<Course> existingCourses = courseDao.findAll();
            List<Teacher> existingTeachers = teacherDao.findAll();
            List<Lecture> existingLectures = lectureDao.findAll();

            saveCollection(existingStudents, STUDENT_FILE);
            saveCollection(existingCourses, COURSE_FILE);
            saveCollection(existingTeachers, TEACHER_FILE);
            saveCollection(existingLectures, LECTURE_FILE);

        }
    }

    private <T> void saveCollection(List<T> collectionData, File file) {
        try{
            objectMapper.writeValue(file, collectionData);
            LOGGER.info("Collection data successfully persisted");
        }catch (IOException ex){
            LOGGER.error(ex.getMessage());
        }
    }

}

