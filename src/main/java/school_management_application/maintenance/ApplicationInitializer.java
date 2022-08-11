package school_management_application.maintenance;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import school_management_application.data.dao.course.CourseDaoList;
import school_management_application.data.dao.lecture.LectureDaoList;
import school_management_application.data.dao.student.StudentDaoList;
import school_management_application.data.dao.teacher.TeacherDaoList;
import school_management_application.model.Course;
import school_management_application.model.Lecture;
import school_management_application.model.Student;
import school_management_application.model.Teacher;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static school_management_application.maintenance.StaticResources.*;

@Configuration
public class ApplicationInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializer.class);

    private final ObjectMapper objectMapper;


    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Autowired
    public ApplicationInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    @Primary
    public StudentDaoList studentDaoList(){
        return new StudentDaoList();
    }

    @Bean
    @Primary
    public CourseDaoList courseDaoList(){
        return new CourseDaoList();
    }

    @Bean
    @Primary
    public TeacherDaoList teacherDaoList(){
        return new TeacherDaoList();
    }

    @Bean
    @Primary
    public LectureDaoList lectureDaoList(){
        return new LectureDaoList();
    }

    List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        JavaType type;

        if(!activeProfile.equals("test")){
            type = objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class);
            List<Student> students = readCollection(STUDENT_FILE, type);

            type = objectMapper.getTypeFactory().constructCollectionType(List.class, Course.class);
            List<Course> courses = readCollection(COURSE_FILE, type);

            type = objectMapper.getTypeFactory().constructCollectionType(List.class, Teacher.class);
            List<Teacher> teachers = readCollection(TEACHER_FILE, type);

            type = objectMapper.getTypeFactory().constructCollectionType(List.class, Lecture.class);
            List<Lecture> lectures = readCollection(LECTURE_FILE, type);

            if(students != null){
                StudentDaoList.setStudents(students);
            }

            if(courses != null){
                CourseDaoList.setCourses(courses);
            }

            if(teachers != null){
                TeacherDaoList.setTeachers(teachers);
            }

            if(lectures != null){
                LectureDaoList.setLectures(lectures);
            }
        }

    }

    private <T> List<T> readCollection(File file, JavaType type){

        try{
            List<T> collectionData = objectMapper.readValue(file, type);
            LOGGER.info("Collection data from file successfully loaded");
            return collectionData;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }

        return null;
    }

}