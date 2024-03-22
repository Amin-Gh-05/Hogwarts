package User;

public interface AccountManagement {
    boolean validatePassword(String enteredPassword);

    void changeUsername(String newUsername);

    void changePassword(String oldPassword, String newPassword);
}
