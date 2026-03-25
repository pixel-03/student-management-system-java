package service;

public class AuthService {

    private final String USERNAME = "admin";
    private final String PASSWORD = "1234";

    public boolean login(String username, String password) {
        return USERNAME.equals(username) && PASSWORD.equals(password);
    }
}
