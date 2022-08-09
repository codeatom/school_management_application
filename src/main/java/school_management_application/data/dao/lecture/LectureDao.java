package school_management_application.data.dao.lecture;

import school_management_application.model.Lecture;

import java.util.List;

public interface LectureDao {
    Lecture saveLecture(Lecture lecture);
    List<Lecture> findByTitle(String title);
    Lecture findById(int id);
    List<Lecture> findAll();
    boolean deleteLecture(Lecture lecture);
}
