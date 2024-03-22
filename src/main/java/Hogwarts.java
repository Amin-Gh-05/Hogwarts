import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hogwarts {
    private static final List<Account> userList = new ArrayList<>();
    private static final List<Teacher> teacherList = new ArrayList<>();
    private static final List<Course> courseList = new ArrayList<>();
    private static final List<Student> studentList = new ArrayList<>();
    private static final List<Assistant> assistantList = new ArrayList<>();

    // getters
    public static List<Teacher> getTeacherList() {
        return teacherList;
    }

    public static List<Course> getCourseList() {
        return courseList;
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static List<Assistant> getAssistantList() {
        return assistantList;
    }

    // view users
    public static void viewAllTeachers() {
        System.out.println("> Here's list of all teachers:");
        for (Teacher t : teacherList) {
            System.out.println("- " + t.getUsername());
        }
    }

    public static void viewAllStudents() {
        System.out.println("> Here's list of all students:");
        for (Student s : studentList) {
            System.out.println("- " + s.getUsername() + " (" + s.getHouse() + ")");
        }
    }

    public static void viewAllCourses() {
        System.out.println("> Here's list of all courses:");
        for (Course c : courseList) {
            System.out.println("- " + c.getTitle() + ", Teacher: " + c.getTeacher().getUsername());
        }
    }

    public static void viewCourseStudents(Course course) {
        System.out.println("> Here's the list of students enrolled for the course " + course.getTitle());
        for (Student s : course.getStudentList()) {
            System.out.println("- " + s.getUsername() + " (" + s.getHouse() + ")");
        }
    }

    // add accounts
    public static void addTeacher(String username, String password) {
        Teacher teacher = new Teacher(username, password);
        userList.add(teacher);
        teacherList.add(teacher);
    }

    public static void addStudent(String username, String password) {
        Student student = new Student(username, password);
        userList.add(student);
        studentList.add(student);
    }

    public static void addAssistant(String username, String password) {
        Assistant assistant = new Assistant(username, password);
        userList.add(assistant);
        assistantList.add(assistant);
    }

    public static void addCourse(String title) {
        Course course = new Course(title);
        courseList.add(course);
    }

    // get info via id
    public static void getUserInfoViaID(UUID uuid) {
        for (Account a : userList) {
            if (a.getAccountID().equals(uuid)) {
                System.out.println("> Username: " + a.getUsername() + ", " + a.getUser());
                break;
            }
        }
    }

    public static void getCourseInfoViaID(UUID uuid) {
        for (Course c : courseList) {
            if (c.getCourseID().equals(uuid)) {
                System.out.println("> Title " + c.getTitle() + ", Teacher: " + c.getTeacher());
                viewCourseStudents(c);
                break;
            }
        }
    }

    public static boolean isUsernameAvailable(String username) {
        for (Account user : userList) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}
