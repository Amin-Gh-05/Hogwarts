import java.util.Scanner;

public class Main {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
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
            System.out.println("> Welcome to Hogwarts school management system");
            System.out.println("- Log in");
            System.out.println("- Sign up");
            System.out.println("- Quit");
            System.out.print("> What do you wish to do? ");
            String choice = read.nextLine();
            if (choice.equals("Log in")) {
                System.out.println("- Teacher");
                System.out.println("- Student");
                System.out.println("- Assistant");
                System.out.print("> Which user are you? ");
                String user = read.nextLine();
                System.out.print("> Enter your username: ");
                String username = read.nextLine();
                System.out.print("> Enter your password: ");
                String password = read.nextLine();
                switch (user) {
                    case "Teacher" -> {
                        boolean inList = false;
                        for (Teacher t : Hogwarts.getTeacherList()) {
                            if (t.getUsername().equals(username)) {
                                inList = true;
                                if (t.validatePassword(password)) {
                                    teacherMenu(t);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    case "Student" -> {
                        boolean inList = false;
                        for (Student s : Hogwarts.getStudentList()) {
                            if (s.getUsername().equals(username)) {
                                inList = true;
                                if (s.validatePassword(password)) {
                                    studentMenu(s);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    case "Assistant" -> {
                        boolean inList = false;
                        for (Assistant a : Hogwarts.getAssistantList()) {
                            if (a.getUsername().equals(username)) {
                                inList = true;
                                if (a.validatePassword(password)) {
                                    assistantMenu(a);
                                } else {
                                    System.out.println("> Wrong password!");
                                }
                            }
                        }
                        if (!(inList)) {
                            System.out.println("> Username not found!");
                        }
                    }
                    default -> System.out.println("> Wrong input!");
                }
            } else if (choice.equals("Sign up")) {
                System.out.println("- Teacher");
                System.out.println("- Student");
                System.out.println("- Assistant");
                System.out.print("> Which user do you wish to be? ");
                String user = read.nextLine();
                System.out.print("> Enter your username: ");
                String username = read.nextLine();
                System.out.print("> Enter your password: ");
                String password = read.nextLine();
                System.out.print("> Write a line to admin for your request: ");
                String text = read.nextLine();
                switch (user) {
                    case "Teacher" -> {
                        Teacher teacher = new Teacher(username, password);
                        teacher.signUp(text);
                    }
                    case "Student" -> {
                        Student student = new Student(username, password);
                        student.signUp(text);
                    }
                    case "Assistant" -> {
                        Assistant assistant = new Assistant(username, password);
                        assistant.signUp(text);
                    }
                    default -> System.out.println("> Wrong input!");
                }
            } else if (choice.equals("Quit")) {
                System.out.println("> Thank you for using the app!");
                break;
            } else {
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
            System.out.println("- Logout");
            System.out.print("> What do you want to do? ");
            String choice = read.nextLine();
            if (choice.equals("View courses")) {
                teacher.viewCourses();
            } else if (choice.equals("Take course")) {
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
                    System.out.println("> Course not available!");
                }
            } else if (choice.equals("Score students")) {
                teacher.viewCourses();
                System.out.print("> Choose the course: ");
                String course = read.nextLine();
                boolean inList = false;
                for (Course c : teacher.getCourseList()) {
                    if (c.getTitle().equals(course)) {
                        c.viewStudents();
                        System.out.print("> Which student do you want to score? ");
                        String student = read.nextLine();
                        System.out.print("> Enter score (0 - 20): ");
                        double score = read.nextDouble();
                        read.nextLine();
                        while (score < 0 || score > 20) {
                            System.out.print("> Please enter a valid score: ");
                            score = read.nextDouble();
                            read.nextLine();
                        }
                        boolean inList2 = false;
                        for (Student s : c.getStudentList()) {
                            if (s.getUsername().equals(student)) {
                                teacher.scoreStudent(c, s, score);
                                inList2 = true;
                            }
                        }
                        if (!(inList2)) {
                            System.out.println("> Student not found!");
                        }
                        inList = true;
                    }
                }
                if (!(inList)) {
                    System.out.println("> Course not found!");
                }
            } else if (choice.equals("Show score")) {
                System.out.println("> This is your total score: " + teacher.getScore());
            } else if (choice.equals("Request course")) {
                System.out.print("> Enter the title of course you want: ");
                String title = read.nextLine();
                teacher.requestCourse(title);
            } else if (choice.equals("Logout")) {
                break;
            } else {
                System.out.println("> Invalid input!");
            }
        }
    }

    public static void studentMenu(Student student) {
        //todo
    }

    public static void assistantMenu(Assistant assistant) {
        //todo
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
