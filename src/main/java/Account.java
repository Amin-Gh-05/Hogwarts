import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Account implements AccountManagement {
    private String username;
    private String password;
    private final String user;
    private final UUID accountID;

    public Account(String username, String password, String user) {
        this.username = username;
        this.password = DigestUtils.sha256Hex(password);
        this.user = user;
        this.accountID = UUID.randomUUID();
    }

    @Override
    public boolean validatePassword(String enteredPassword) {
        return DigestUtils.sha256Hex(enteredPassword).equals(this.password);
    }

    @Override
    public void changeUsername(String newUsername) {
        this.username = newUsername;
        System.out.println("> Your username changed to " + this.username);
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        if (validatePassword(oldPassword)) {
            this.password = DigestUtils.sha256Hex(newPassword);
            System.out.println("> Password successfully updated");
        } else {
            System.out.println("> Wrong password!");
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getUser() {
        return this.user;
    }

    public UUID getAccountID() {
        return this.accountID;
    }

    public void viewCourses() {
        System.out.println("> Here's the list of all courses:");
        for (Course c : Hogwarts.getCourseList()) {
            System.out.println("- " + c.getTitle());
        }
    }

    public void signUp(String text) {
        List<String> mail = new ArrayList<>();
        mail.add(this.user);
        mail.add("sign_up");
        mail.add(this.username);
        mail.add(this.password);
        Assistant.getInboxList().add(mail);
        System.out.println("> Your sign-up request was successfully sent for admin");
    }
}
