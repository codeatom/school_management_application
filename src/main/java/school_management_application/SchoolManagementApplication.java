package school_management_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school_management_application.data.service.course.CourseService;
import school_management_application.data.service.lecture.LectureService;
import school_management_application.data.service.student.StudentService;
import school_management_application.data.service.teacher.TeacherService;
import school_management_application.model.Course;
import school_management_application.model.Lecture;
import school_management_application.model.Student;
import school_management_application.model.Teacher;

import java.util.Locale;
import java.util.Scanner;

@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementApplication.class, args);
	}

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private LectureService lectureService;

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean appIsRunning = true;
		String choice;


		System.out.println("enter S to add Student, C to add course, T to add teacher or L to add lecture");
		System.out.println("enter q to quit at anytime");

		while (appIsRunning) {
			choice = scanner.nextLine().toUpperCase();

			switch (choice){
				case "S":
					Student student = createAStudent();
					studentService.saveStudent(student.getName(), student.getEmail(), student.getAddress());
					System.out.println("enter S to add Student, C to add course, T to add teacher or L to add lecture");
					break;
				case "C":
					Course course = createACourse();
					courseService.saveCourse(course.getCourseName(), course.getStartDate(), course.getWeekDuration());
					System.out.println("enter S to add Student, C to add course, T to add teacher or L to add lecture");
					break;
				case "T":
					Teacher teacher = createATeacher();
					teacherService.saveTeacher(teacher.getName(), teacher.getEmail(), teacher.getAddress());
					System.out.println("enter S to add Student, C to add course, T to add teacher or L to add lecture");
					break;
				case "L":
					Lecture lecture = createALecture();
					lectureService.saveLecture(lecture.getTitle());
					System.out.println("enter S to add Student, C to add course, T to add teacher or L to add lecture");
					break;
				case "Q":
					appIsRunning = false;
					break;
				default:
					System.out.println("You entered a wrong input. Please try again");
			}
		}

		displayStudents();
		displayCourses();
		displayTeachers();
		displayLectures();

		//Student student = studentService.saveStudent("Christopher", "chris@lexicon.com", "lexico växjö");
		//Course course = courseService.saveCourse("Java development", null, 24);
		//Teacher teacher = teacherService.saveTeacher("JavaTeacher", "jteacher@lexicon.com", "lexico växjö");
		//Lecture lecture = lectureService.saveLecture("Java lecture");
	}

	public Student createAStudent() {
		String name;
		String email;
		String address;

		Scanner scanner = new Scanner(System.in);

		System.out.println("enter Student name");
		name = scanner.nextLine();

		System.out.println("enter Student email");
		email = scanner.nextLine();

		System.out.println("enter Student address");
		address = scanner.nextLine();

		return new Student(0, name, email, address);
	}

	public Teacher createATeacher() {
		String name;
		String email;
		String address;

		Scanner scanner = new Scanner(System.in);

		System.out.println("enter teacher name");
		name = scanner.nextLine();

		System.out.println("enter teacher email");
		email = scanner.nextLine();

		System.out.println("enter teacher address");
		address = scanner.nextLine();

		return new Teacher(0, name, email, address);
	}

	public Course createACourse() {
		String name;
		String date;
		String duration;

		Scanner scanner = new Scanner(System.in);

		System.out.println("enter course name");
		name = scanner.nextLine();

		return new Course(0, name, null, 0);
	}

	public Lecture createALecture() {
		String title;

		Scanner scanner = new Scanner(System.in);

		System.out.println("enter lecture title");
		title = scanner.nextLine();

		return new Lecture(0, title);
	}

	public void displayStudents() {
		System.out.println("******* All Students *******");
		for (Student student : studentService.findAll()) {
			System.out.println(student.toString());
		}
	}

	public void displayCourses() {
		System.out.println("******* All Courses *******");
		for (Course course : courseService.findAll()) {
			System.out.println(course.toString());
		}
	}

	public void displayTeachers() {
		System.out.println("******* All Teachers *******");
		for (Teacher teacher : teacherService.findAll()) {
			System.out.println(teacher.toString());
		}
	}

	public void displayLectures() {
		System.out.println("******* All Lectures *******");
		for (Lecture lecture : lectureService.findAll()) {
			System.out.println(lecture.toString());
		}
	}

}
