package service;

import java.util.HashMap;
import java.security.MessageDigest;

public class AuthService {

    private HashMap<String, String> users = new HashMap<>();

    public AuthService() {
        // Default user (password = 1234)
        users.put("admin", hashPassword("1234"));
    }

    // 🔐 LOGIN
    public boolean login(String username, String password) {
        if (users.containsKey(username)) {
            String hashedInput = hashPassword(password);
            return users.get(username).equals(hashedInput);
        }
        return false;
    }

    // 📝 REGISTER
    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
        } else {
            users.put(username, hashPassword(password));
            System.out.println("User registered successfully!");
        }
    }

    // 🔐 HASH FUNCTION (SHA-256)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error hashing password");
        }
    }
}
