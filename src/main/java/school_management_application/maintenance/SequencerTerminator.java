package school_management_application.maintenance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import school_management_application.data.sequencer.CourseSequencer;
import school_management_application.data.sequencer.LectureSequencer;
import school_management_application.data.sequencer.StudentSequencer;
import school_management_application.data.sequencer.TeacherSequencer;

import javax.annotation.PreDestroy;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static school_management_application.maintenance.StaticResources.SEQUENCERS_FILE;

@Component
public class SequencerTerminator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTerminator.class);

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @PreDestroy
    public void destroy(){
        Properties properties = new Properties();

        if(!activeProfile.equals("test")){
            properties.setProperty("currentStudentId", String.valueOf(StudentSequencer.getStudentSequencer()));
            properties.setProperty("currentCourseId", String.valueOf(CourseSequencer.getCourseSequencer()));
            properties.setProperty("currentTeacherId", String.valueOf(TeacherSequencer.getTeacherSequencer()));
            properties.setProperty("currentLectureId", String.valueOf(LectureSequencer.getLectureSequencer()));

            try(FileWriter writer = new FileWriter(SEQUENCERS_FILE)){
                properties.store(writer, "Latest sequencer values");
                LOGGER.info("Latest sequencer values stored");
            }catch (IOException ex){
                LOGGER.error(ex.getMessage());
            }
        }

    }
}