package school_management_application.data.service.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school_management_application.data.dao.course.CourseDao;
import school_management_application.data.dao.lecture.LectureDao;
import school_management_application.data.sequencer.LectureSequencer;
import school_management_application.model.Course;
import school_management_application.model.Lecture;

import java.util.List;

@Service
public class LectureManager implements LectureService{

    private final LectureDao lectureDao;
    private final CourseDao courseDao;

    @Autowired
    public LectureManager(LectureDao lectureDao, CourseDao courseDao) {
        this.lectureDao = lectureDao;
        this.courseDao = courseDao;
    }

    @Override
    public Lecture saveLecture(String title) {
        Lecture lecture = new Lecture(LectureSequencer.nextLectureId(), title);
        return lectureDao.saveLecture(lecture);
    }

    @Override
    public List<Lecture> findByTitle(String title) {
        return lectureDao.findByTitle(title);
    }

    @Override
    public Lecture findById(int id) {
        return lectureDao.findById(id);
    }

    @Override
    public List<Lecture> findAll() {
        return lectureDao.findAll();
    }

    @Override
    public boolean deleteLecture(Lecture lecture) {
        for(Course course : courseDao.findAll()){
            course.getLectures().remove(lecture);
        }
        return lectureDao.deleteLecture(lecture);
    }
}
