import Course.Course;
import Hogwarts.Hogwarts;
import User.Assistant;
import User.Student;
import User.Teacher;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        Hogwarts.addAssistant("admin", "admin");
        runMenu();
    }

    public static void runMenu() {
        // main loop of the menu
        while (true) {
            clearConsole();
            // logo and welcome
            System.out.println("""
                    .---------------------------------------------------------------------.
                    | __  __                                               __             |
                    |/\\ \\/\\ \\                                             /\\ \\__          |
                    |\\ \\ \\_\\ \\    ___      __   __  __  __     __     _ __\\ \\ ,_\\   ____  |
                    | \\ \\  _  \\  / __`\\  /'_ `\\/\\ \\/\\ \\/\\ \\  /'__`\\  /\\`'__\\ \\ \\/  /',__\\ |
                    |  \\ \\ \\ \\ \\/\\ \\L\\ \\/\\ \\L\\ \\ \\ \\_/ \\_/ \\/\\ \\L\\.\\_\\ \\ \\/ \\ \\ \\_/\\__, `\\|
                    |   \\ \\_\\ \\_\\ \\____/\\ \\____ \\ \\___x___/'\\ \\__/.\\_\\\\ \\_\\  \\ \\__\\/\\____/|
                    |    \\/_/\\/_/\\/___/  \\/___L\\ \\/__//__/   \\/__/\\/_/ \\/_/   \\/__/\\/___/ |
                    |                      /\\____/                                        |
                    |                      \\_/__/                                         |
                    '---------------------------------------------------------------------'""");
            System.out.println("> Welcome to Hogwarts.Hogwarts school management system");
            System.out.println("- Log in");
            System.out.println("- Sign up");
            System.out.println("- Quit");
            System.out.print("> What do you wish to do? ");
            String choice = read.nextLine();
            // check the choice
            if (choice.equals("Log in")) {
                System.out.println("- Teacher");
                System.out.println("- Student");
                System.out.println("- Assistant");
                System.out.print("> Which user are you? ");
                String user = read.nextLine();
                // log into account based on user type
                switch (user) {
                    case "Teacher" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        boolean inList = false;
                        for (Teacher t : Hogwarts.getTeacherList()) {
                            if (t.getUsername().equals(username)) {
                                inList = true;
                                if (t.validatePassword(password)) {
                                    teacherMenu(t);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                                break;
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    case "Student" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        boolean inList = false;
                        for (Student s : Hogwarts.getStudentList()) {
                            if (s.getUsername().equals(username)) {
                                inList = true;
                                if (s.validatePassword(password)) {
                                    studentMenu(s);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                                break;
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    case "Assistant" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        boolean inList = false;
                        for (Assistant a : Hogwarts.getAssistantList()) {
                            if (a.getUsername().equals(username)) {
                                inList = true;
                                if (a.validatePassword(password)) {
                                    assistantMenu(a);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                                break;
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    default -> System.out.println("> Invalid input!");
                }
            }
            // sign up request based on user type
            else if (choice.equals("Sign up")) {
                System.out.println("- Teacher");
                System.out.println("- Student");
                System.out.println("- Assistant");
                System.out.print("> Which user do you wish to be? ");
                String user = read.nextLine();
                switch (user) {
                    case "Teacher" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        Teacher teacher = new Teacher(username, password);
                        teacher.signUp();
                    }
                    case "Student" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        Student student = new Student(username, password);
                        student.signUp();
                    }
                    case "Assistant" -> {
                        System.out.print("> Enter your username: ");
                        String username = read.nextLine();
                        System.out.print("> Enter your password: ");
                        String password = read.nextLine();
                        Assistant assistant = new Assistant(username, password);
                        assistant.signUp();
                    }
                    default -> System.out.println("> Wrong input!");
                }
            }
            // break the loop
            else if (choice.equals("Quit")) {
                System.out.println("> Thank you for using the app!");
                break;
            }
            // continue if input is not valid
            else {
                System.out.println("> Invalid input!");
            }
        }
    }

    public static void teacherMenu(Teacher teacher) {
        while (true) {
            clearConsole();
            System.out.println("> Welcome " + teacher.getUsername() + " to your dashboard");
            System.out.println("- View courses");
            System.out.println("- Take course");
            System.out.println("- Score students");
            System.out.println("- Show score");
            System.out.println("- Request course");
            System.out.println("- Check inbox");
            System.out.println("- Logout");
            System.out.print("> What do you wish to do? ");
            String choice = read.nextLine();
            // take action of teacher based on input
            if (choice.equals("View courses")) {
                teacher.viewCourses();
            }
            // take course based on title
            else if (choice.equals("Take course")) {
                System.out.println("> Here's the list of courses available:");
                for (Course c : Hogwarts.getCourseList()) {
                    if (c.getTeacher().getUsername().equals("Unknown")) {
                        System.out.println("- " + c.getTitle());
                    }
                }
                System.out.print("> What course do you want to take? ");
                String course = read.nextLine();
                boolean inList = false;
                for (Course c : Hogwarts.getCourseList()) {
                    if (c.getTitle().equals(course)) {
                        teacher.takeCourse(c);
                        inList = true;
                        break;
                    }
                }
                if (!(inList)) {
                    System.out.println("> Course.Course not available!");
                }
            }
            // score student based on course and username
            else if (choice.equals("Score students")) {
                teacher.viewCourses();
                System.out.print("> Choose the course: ");
                String course = read.nextLine();
                boolean inCourseList = false;
                for (Course c : teacher.getCourseList()) {
                    if (c.getTitle().equals(course)) {
                        c.viewStudents();
                        System.out.print("> Which student do you want to score? ");
                        String student = read.nextLine();
                        System.out.print("> Enter score (0 - 20): ");
                        try {
                            double score = read.nextDouble();
                            read.nextLine();
                            // check validity of scores
                            while (score < 0 || score > 20) {
                                System.out.print("> Please enter a valid score: ");
                                score = read.nextDouble();
                                read.nextLine();
                            }
                            boolean inStudentList = false;
                            for (Student s : c.getStudentList()) {
                                if (s.getUsername().equals(student)) {
                                    teacher.scoreStudent(c, s, score);
                                    inStudentList = true;
                                    break;
                                }
                            }
                            if (!(inStudentList)) {
                                System.out.println("> Student not found!");
                            }
                            inCourseList = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                        }
                    }
                }
                if (!(inCourseList)) {
                    System.out.println("> Course not found!");
                }
            }
            // show score of the teacher
            else if (choice.equals("Show score")) {
                System.out.println("> This is your total score: " + teacher.getScore());
            }
            // request course as a teacher
            else if (choice.equals("Request course")) {
                System.out.print("> Enter the title of course you want: ");
                String title = read.nextLine();
                teacher.requestCourse(title);
            } else if (choice.equals("Check inbox")) {
                teacher.viewComments();
            }
            // break the loop
            else if (choice.equals("Logout")) {
                break;
            }
            // continue the loop if input is not valid
            else {
                System.out.println("> Invalid input!");
            }
            // keep logs available before clearing console
            System.out.print("> Press enter to return to teacher dashboard...");
            read.nextLine();
        }
    }

    public static void studentMenu(Student student) {
        while (true) {
            clearConsole();
            System.out.println("> Welcome " + student.getUsername() + " to your dashboard");
            System.out.println("- Take course");
            System.out.println("- View courses");
            System.out.println("- View teachers");
            System.out.println("- Take quiz");
            System.out.println("- Comment teacher");
            System.out.println("- Request course");
            System.out.println("- Score teacher");
            System.out.println("- Logout");
            System.out.print("> What do you wish to do? ");
            String choice = read.nextLine();
            // take the course based on title
            if (choice.equals("Take course")) {
                Hogwarts.viewAllCourses();
                String course = read.nextLine();
                boolean inList = false;
                for (Course c : Hogwarts.getCourseList()) {
                    if (c.getTitle().equals(course)) {
                        inList = true;
                        if (!(student.getCourseList().contains(c))) {
                            student.takeCourse(c);
                        } else {
                            System.out.println("> You already have this course");
                        }
                        break;
                    }
                }
                if (!(inList)) {
                    System.out.println("> Course not found!");
                }
            }
            // view courses student have enrolled for
            else if (choice.equals("View courses")) {
                student.viewCourses();
            }
            // take sorting quiz
            else if (choice.equals("Take quiz")) {
                student.takeQuiz();
            }
            // leave comment for teacher
            else if (choice.equals("Comment teacher")) {
                System.out.print("> Who do you wish to comment? ");
                String teacher = read.nextLine();
                System.out.print("> Write your comment: ");
                String comment = read.nextLine();
                boolean inList = false;
                for (Teacher t : Hogwarts.getTeacherList()) {
                    if (t.getUsername().equals(teacher)) {
                        student.commentTeacher(t, comment);
                        inList = true;
                        break;
                    }
                }
                if (!(inList)) {
                    System.out.println("> Teacher not found!");
                }
            }
            // request course as student
            else if (choice.equals("Request course")) {
                System.out.print("> Enter the title of course: ");
                String course = read.nextLine();
                student.requestCourse(course);
            }
            // score the teacher
            else if (choice.equals("Score teacher")) {
                System.out.print("> Select the course: ");
                String course = read.nextLine();
                boolean inList = false;
                for (Course c : Hogwarts.getCourseList()) {
                    if (c.getTitle().equals(course)) {
                        System.out.print("> Enter the score (0 - 10): ");
                        try {
                            int score = read.nextInt();
                            read.nextLine();
                            while (score < 0 || score > 10) {
                                System.out.print("> Please enter a valid score: ");
                                score = read.nextInt();
                                read.nextLine();
                            }
                            // check if student has entered a valid score
                            student.scoreTeacher(c, score);
                            inList = true;
                            break;
                        } catch (Exception e) {
                            System.out.println("> Invalid input!");
                        }
                    }
                }
                if (!(inList)) {
                    System.out.println("> Course not found!");
                }
            }
            // break the loop
            else if (choice.equals("Logout")) {
                break;
            }
            // invalid input
            else {
                System.out.println("> Invalid input!");
            }
            // keep logs available before clearing console
            System.out.print("> Press enter to return to student dashboard...");
            read.nextLine();
        }
    }

    public static void assistantMenu(Assistant assistant) {
        while (true) {
            clearConsole();
            System.out.println("> Welcome " + assistant.getUsername() + " to your dashboard");
            System.out.println("- View");
            System.out.println("- Remove");
            System.out.println("- Get info");
            System.out.println("- Create course");
            System.out.println("- Logout");
            System.out.print("> What do you wish to do? ");
            String choice = read.nextLine();
            // view students, teachers, inbox
            if (choice.equals("View")) {
                System.out.println("- Students");
                System.out.println("- Teachers");
                System.out.println("- Courses");
                System.out.println("- Inbox");
                System.out.print("> Which one do you wish to view? ");
                String view = read.nextLine();
                switch (view) {
                    case "Students" -> Hogwarts.viewAllStudents();
                    case "Teachers" -> Hogwarts.viewAllTeachers();
                    case "Courses" -> Hogwarts.viewAllCourses();
                    case "Inbox" -> {
                        for (List<String> mail : Assistant.getInboxList()) {
                            switch (mail.get(1)) {
                                case "sign_up" -> {
                                    switch (mail.getFirst()) {
                                        case "teacher" -> {
                                            System.out.println("> " + mail.get(2) + " wants to sign up as teacher");
                                            System.out.print("> Do you confirm (Y/N)? ");
                                            String confirm = read.nextLine();
                                            if (confirm.equals("Y")) {
                                                assistant.signUpTeacher(mail.get(2), mail.get(3));
                                                Assistant.getInboxList().remove(mail);
                                            }
                                        }
                                        case "student" -> {
                                            System.out.println("> " + mail.get(2) + " wants to sign up as student");
                                            System.out.print("> Do you confirm (Y/N)? ");
                                            String confirm = read.nextLine();
                                            if (confirm.equals("Y")) {
                                                assistant.signUpStudent(mail.get(2), mail.get(3));
                                                Assistant.getInboxList().remove(mail);
                                            }
                                        }
                                        case "assistant" -> {
                                            System.out.println("> " + mail.get(2) + " wants to sign up as assistant");
                                            System.out.print("> Do you confirm (Y/N)? ");
                                            String confirm = read.nextLine();
                                            if (confirm.equals("Y")) {
                                                assistant.signUpAssistant(mail.get(2), mail.get(3));
                                                Assistant.getInboxList().remove(mail);
                                            }
                                        }
                                        default -> System.out.println("> Invalid user!");
                                    }
                                }
                                case "request_course" -> {
                                    switch (mail.getFirst()) {
                                        case "teacher" -> {
                                            System.out.println("> " + mail.get(2) + " is requested by a teacher");
                                            System.out.print("> Do you confirm (Y/N)? ");
                                            String confirm = read.nextLine();
                                            if (confirm.equals("Y")) {
                                                assistant.createCourse(mail.get(2));
                                            }
                                            Assistant.getInboxList().remove(mail);
                                        }
                                        case "student" -> {
                                            System.out.println("> " + mail.get(2) + " is requested by a student");
                                            System.out.print("> Do you confirm (Y/N)? ");
                                            String confirm = read.nextLine();
                                            if (confirm.equals("Y")) {
                                                assistant.createCourse(mail.get(2));
                                            }
                                            Assistant.getInboxList().remove(mail);
                                        }
                                        default -> System.out.println("> Invalid user!");
                                    }
                                }
                                default -> System.out.println("> Invalid mail!");
                            }
                        }
                    }
                    default -> System.out.println("> Invalid input");
                }
            }
            // remove students and teachers
            else if (choice.equals("Remove")) {
                System.out.println("- User.Teacher");
                System.out.println("- User.Student");
                System.out.print("> Which user do you wish to remove? ");
                String user = read.nextLine();
                switch (user) {
                    case "Teacher" -> {
                        System.out.print("> Enter username: ");
                        String username = read.nextLine();
                        boolean inList = false;
                        for (Teacher t : Hogwarts.getTeacherList()) {
                            if (t.getUsername().equals(username)) {
                                assistant.removeTeacher(t);
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Teacher not found!");
                        }
                    }
                    case "Student" -> {
                        System.out.print("> Enter username: ");
                        String username = read.nextLine();
                        boolean inList = false;
                        for (Student s : Hogwarts.getStudentList()) {
                            if (s.getUsername().equals(username)) {
                                assistant.removeStudent(s);
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Student not found!");
                        }
                    }
                    default -> System.out.println("> Invalid input");
                }
            }
            // find course or user via id
            else if (choice.equals("Get info")) {
                System.out.println("- User");
                System.out.println("- Course.Course");
                System.out.print("> What are looking for? ");
                String object = read.nextLine();
                switch (object) {
                    case "User" -> {
                        System.out.print("> Enter id: ");
                        String id = read.nextLine();
                        Hogwarts.getUserInfoViaID(UUID.fromString(id));
                    }
                    case "Course" -> {
                        System.out.print("> Enter id: ");
                        String id = read.nextLine();
                        Hogwarts.getCourseInfoViaID(UUID.fromString(id));
                    }
                    default -> System.out.println("> Invalid input!");
                }
            }
            // create a new course
            else if (choice.equals("Create course")) {
                System.out.print("> Enter the title: ");
                String course = read.nextLine();
                assistant.createCourse(course);
            }
            // break the loop
            else if (choice.equals("Logout")) {
                break;
            }
            // invalid user input
            else {
                System.out.println("Invalid input!");
            }
            // keep logs available before clearing console
            System.out.print("> Press enter to return to assistant dashboard...");
            read.nextLine();
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
