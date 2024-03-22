import java.util.ArrayList;
import java.util.List;

public class Assistant extends Account {
    private static final List<List<String>> inboxList = new ArrayList<>();

    public Assistant(String username, String password) {
        super(username, password, "assistant");
    }

    // getters
    public static List<List<String>> getInboxList() {
        return inboxList;
    }

    // view all
    public void viewStudents(Course course) {
        System.out.println("> Here's the list of students enrolled for the course " + course.getTitle());
        for (Student s : course.getStudentList()) {
            System.out.println("- " + s.getUsername());
        }
    }

    // sign up other accounts
    public void signUpTeacher(String username, String password) {
        Hogwarts.addTeacher(username, password);
        System.out.println("> Teacher " + username + " was successfully signed up");
    }

    public void signUpStudent(String username, String password) {
        Hogwarts.addStudent(username, password);
        System.out.println("> Student " + username + " was successfully signed up");
    }

    public void signUpAssistant(String username, String password) {
        Hogwarts.addAssistant(username, password);
        System.out.println("> Assistant " + username + " was successfully signed up");
    }

    public void createCourse(String title) {
        Hogwarts.addCourse(title);
        System.out.println("> Course " + title + " was successfully added");
    }

    // remove other accounts
    public void removeTeacher(Teacher teacher) {
        Hogwarts.getTeacherList().remove(teacher);
        System.out.println("> Teacher " + teacher.getUsername() + " was successfully removed");
    }

    public void removeStudent(Student student) {
        Hogwarts.getStudentList().remove(student);
        System.out.println("> Student " + student.getUsername() + " was successfully removed");
    }
}
