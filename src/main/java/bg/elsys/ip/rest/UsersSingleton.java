package bg.elsys.ip.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class UsersSingleton {
    private static UsersSingleton instance = null;

    public List<User> users;
    public User currentUser;

    private UsersSingleton() {
        users = new ArrayList<>();
    }

    public static UsersSingleton getInstance() {
        if(instance == null)
            instance = new UsersSingleton();

        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void handleGoogleUser(String email, String accessToken) {
        System.out.println("Handling user...");
        System.out.println("Email: " + email + " Access token: " + accessToken);

        Optional<User> googleUser = getUsers().stream().filter(u -> u.getEmail().equals(email)).findFirst();

        if(googleUser.isPresent()) {
            System.out.println("Logging user in...");
            setCurrentUser(googleUser.get());
        }
        else {
            System.out.println("Creating new user...");
            User newUser = new User(email, accessToken);
            getUsers().add(newUser);
            setCurrentUser(newUser);
        }

        System.out.println(getUsers().size() + " users in list");
        for (User u :
                getUsers()) {
            System.out.println(u);
        }
    }
}
