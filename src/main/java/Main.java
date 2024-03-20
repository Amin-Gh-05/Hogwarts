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
                switch (user) {
                    case "Teacher" -> teacherMenu();
                    case "Student" -> studentMenu();
                    case "Assistant" -> assistantMenu();
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
                System.out.println("> Wrong input!");
            }
        }
    }

    public static void teacherMenu() {
        //todo
    }

    public static void studentMenu() {
        //todo
    }

    public static void assistantMenu() {
        //todo
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
