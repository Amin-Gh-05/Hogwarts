import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    private final String title;
    private final UUID courseID;
    private final List<Student> studentList = new ArrayList<>();
    private final List<Double> scoreList = new ArrayList<>();
    private Teacher teacher;

    public Course(String title) {
        this.title = title;
        this.courseID = UUID.randomUUID();
        this.teacher = new Teacher("Unknown", "Unknown");
    }

    // getters and setters
    public String getTitle() {
        return this.title;
    }

    public UUID getCourseID() {
        return this.courseID;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }

    public List<Double> getScoreList() {
        return this.scoreList;
    }

    // view all
    public void viewStudents() {
        System.out.println("> Here's the list of students enrolled in this course:");
        for (Student s : this.studentList) {
            System.out.println("- " + s.getUsername());
        }
    }
}
