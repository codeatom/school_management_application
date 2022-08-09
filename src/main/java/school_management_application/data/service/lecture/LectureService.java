package school_management_application.data.service.lecture;

import school_management_application.model.Lecture;

import java.util.List;

public interface LectureService {
    Lecture saveLecture(String title);
    List<Lecture> findByTitle(String title);
    Lecture findById(int id);
    List<Lecture> findAll();
    boolean deleteLecture(Lecture lecture);
}
