package school_management_application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {

    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private Teacher teacher;
    private List<Student> students = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();


    public Course() {
    }

    public Course(int id, String courseName, LocalDate startDate, int weekDuration) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.weekDuration = weekDuration;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public boolean enrollStudent(Student students) {
        return true;
    }

    public boolean unenrollStudent(Student students) {
        return true;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && weekDuration == course.weekDuration && Objects.equals(courseName, course.courseName) && Objects.equals(startDate, course.startDate) && Objects.equals(teacher, course.teacher) && Objects.equals(students, course.students) && Objects.equals(lectures, course.lectures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, startDate, weekDuration, teacher, students, lectures);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", startDate=" + startDate +
                ", weekDuration=" + weekDuration +
                ", teacher=" + teacher +
                ", students=" + students +
                ", lectures=" + lectures +
                '}';
    }
}