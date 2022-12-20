package school_management_application.maintenance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import school_management_application.data.sequencer.CourseSequencer;
import school_management_application.data.sequencer.LectureSequencer;
import school_management_application.data.sequencer.StudentSequencer;
import school_management_application.data.sequencer.TeacherSequencer;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static school_management_application.maintenance.StaticResources.SEQUENCERS_FILE;

@Configuration
public class SequencerInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SequencerInitializer.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PostConstruct
    public void init(){
        if(!activeProfile.equals("test")){
            Properties properties = new Properties();

            try(FileReader reader = new FileReader(SEQUENCERS_FILE)){
                properties.load(reader);

            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }

            String currentCourseId = properties.getProperty("currentStudentId");
            String currentStudentId = properties.getProperty("currentCourseId");
            String currentTeacherId = properties.getProperty("currentTeacherId");
            String currentLectureId = properties.getProperty("currentLectureId");

            StudentSequencer.setStudentSequencer(Integer.parseInt(currentStudentId));
            CourseSequencer.setCourseSequencer(Integer.parseInt(currentCourseId));
            TeacherSequencer.setTeacherSequencer(Integer.parseInt(currentTeacherId));
            LectureSequencer.setLectureSequencer(Integer.parseInt(currentLectureId));
        }
    }

}