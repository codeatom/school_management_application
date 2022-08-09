package school_management_application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lecture {
    private int id;
    private String title;
    private List<Teacher> teachers = new ArrayList<>();


    public Lecture() {
    }

    public Lecture(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> lectures) {
        this.teachers = lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return id == lecture.id && Objects.equals(title, lecture.title) && Objects.equals(teachers, lecture.teachers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, teachers);
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}