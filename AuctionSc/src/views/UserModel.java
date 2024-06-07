package src.views;
import java.util.HashSet;
import java.util.Set;
public class UserModel {
    private Set<User> users = new HashSet<>();

    public boolean createUserAccount(String username, String email, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) || user.getEmail().equals(email)) {
                return false;
            }
        }

        User newUser = new User(username, email, password);
        users.add(newUser);

        sendConfirmationEmail(username, email, password);
        return true;
    }

    private void sendConfirmationEmail(String username, String email, String password) {
        System.out.println("Confirmation email sent to " + email + " with username " + username + " and password " + password);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
