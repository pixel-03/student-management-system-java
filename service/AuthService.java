package service;

import java.util.HashMap;

public class AuthService {

    private HashMap<String, String> users = new HashMap<>();

    public AuthService() {
        // Default user
        users.put("admin", "1234");
    }

    public boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
        } else {
            users.put(username, password);
            System.out.println("User registered successfully!");
        }
    }
}
