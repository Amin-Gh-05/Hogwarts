package User;

import Course.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student extends Account {
    private final List<Course> courseList = new ArrayList<>();
    private final List<Double> scoreList = new ArrayList<>();
    private String house = "Unknown";

    public Student(String username, String password) {
        super(username, password, "student");
    }

    @Override
    public void viewCourses() {
        System.out.println("> Here's the list of your courses:");
        for (Course c : this.courseList) {
            System.out.println("- " + c.getTitle() + ", Teacher: " + c.getTeacher());
        }
    }

    // getters
    public List<Course> getCourseList() {
        return this.courseList;
    }

    public List<Double> getScoreList() {
        return this.scoreList;
    }

    public String getHouse() {
        return this.house;
    }

    // specific actions
    public void takeCourse(Course course) {
        this.courseList.add(course);
        this.scoreList.add(0.0);
        course.getStudentList().add(this);
        course.getScoreList().add(0.0);
        System.out.println("> You've now enrolled for " + course.getTitle());
    }

    public void takeQuiz() {
        if (this.house.equals("Unknown")) {
            Random house = new Random();
            int choice = house.nextInt(4);
            switch (choice) {
                case 0 -> this.house = "Gryffindor";
                case 1 -> this.house = "Hufflepuff";
                case 2 -> this.house = "Ravenclaw";
                case 3 -> this.house = "Slytherin";
            }
            System.out.println("> You're now part of " + this.house);
        } else {
            System.out.println("> You're already part of " + this.house);
        }
    }

    public void scoreTeacher(Course course, int score) {
        course.getTeacher().getScoreList().add(score);
        System.out.println("> Scored " + course.getTeacher().getUsername() + " with " + score);
    }

    public void commentTeacher(Teacher teacher, String comment) {
        teacher.getCommentList().add(comment);
        System.out.println("> Your comment was successfully sent for teacher");
    }

    public void requestCourse(String title) {
        List<String> mail = new ArrayList<>();
        mail.add("student");
        mail.add("request_course");
        mail.add(title);
        Assistant.getInboxList().add(mail);
        System.out.println("> Your course request was successfully sent for admin");
    }
}
