package school_management_application.data.dao.lecture;

import school_management_application.model.Lecture;

import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

public class LectureDaoList implements LectureDao{

    private static List<Lecture> lectures = new ArrayList<>();

    public static void setLectures(List<Lecture> lectures) {
        LectureDaoList.lectures = lectures;
    }

    @Override
    public Lecture saveLecture(Lecture lecture) {
        if (lecture == null) throw new IllegalArgumentException("lecture is null");

        lectures.add(lecture);
        return lecture;
    }

    @Override
    public List<Lecture> findByTitle(String title) {
        if (title == null) throw new IllegalArgumentException("title is null");

    //  return lectures.stream().filter(l -> l.getTitle().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());

        List<Lecture> lectureList = new ArrayList<>();

        for(Lecture lecture : lectures){
            if (lecture.getTitle().toLowerCase().contains(title.toLowerCase())){
                lectureList.add(lecture);
            }
        }

        return lectureList;
    }

    @Override
    public Lecture findById(int id) {
        if (id <= 0) throw new IllegalArgumentException("is is not valid");

        for(Lecture lecture : lectures){
            if (lecture.getId() == id){
                return lecture;
            }
        }

        return null;
    }

    @Override
    public List<Lecture> findAll() {
        return lectures;
    }

    @Override
    public boolean deleteLecture(Lecture lecture) {
        if (lecture == null) throw new IllegalArgumentException("lecture is null");
        lectures.remove(lecture);

        return true;
    }
}
