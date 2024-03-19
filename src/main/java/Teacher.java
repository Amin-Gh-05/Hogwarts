import java.util.ArrayList;
import java.util.List;

public class Teacher extends Account {
    private final List<Integer> scoreList = new ArrayList<>();
    private final List<Course> courseList = new ArrayList<>();
    private final List<String> commentList = new ArrayList<>();
    private int score;
    public Teacher(String username, String password) {
        super(username, password, "teacher");
    }

    @Override
    public void viewCourses() {
        System.out.println("> Here's the list of your courses:");
        for (Course c : this.courseList) {
            System.out.println("- " + c.getTitle());
        }
    }

    public List<Integer> getScoreList() {
        return this.scoreList;
    }

    public List<String> getCommentList() {
        return this.commentList;
    }

    public static void signUp(String text, String username, String password) {
        List<String> mail = new ArrayList<>();
        mail.add("teacher");
        mail.add("sign_up");
        mail.add(username);
        mail.add(password);
        Assistant.getInboxList().add(mail);
        System.out.println("> Your sign-up request was successfully sent for admin");
    }

    public void viewStudents(Course course) {
        System.out.println("> Here's the list of students enrolled for the course " + course.getTitle() + ":");
        for (Student s : course.getStudentList()) {
            System.out.println("- " + s.getUsername());
        }
    }

    public void viewComments() {
        System.out.println("> Here's the list of your comments:");
        for (int i = this.commentList.size() - 1; i >= 0; i--) {
            System.out.println("- " + this.commentList.get(i));
        }
    }

    public void takeCourse(Course course) {
        course.setTeacher(this);
        this.courseList.add(course);
        System.out.println("> You're now the teacher of " + course.getTitle());
    }

    public int getScore() {
        score = 0;
        for (int r : this.scoreList) {
            score += r;
        }

        return score / this.scoreList.size();
    }

    public void scoreStudent(Course course, Student student, double score) {
        course.getScoreList().set(course.getStudentList().indexOf(student), score);
        student.getScoreList().set(student.getCourseList().indexOf(course), score);
        System.out.println("> Score " + score + "was submitted for " + student.getUsername() + " in course " + course.getTitle());
    }

    public void requestCourse(String title) {
        List<String> mail = new ArrayList<>();
        mail.add("teacher");
        mail.add("request_course");
        mail.add(title);
        Assistant.getInboxList().add(mail);
        System.out.println("> Your course request was successfully sent for admin");
    }
}
