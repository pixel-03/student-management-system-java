package service;

import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class AuthService {

    private HashMap<String, String> users = new HashMap<>();
    private final String FILE_NAME = "users.txt";

    public AuthService() {
        loadFromFile();
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
            saveToFile();
            System.out.println("User registered successfully!");
        }
    }

    // 💾 SAVE USERS TO FILE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users!");
        }
    }

    // 📂 LOAD USERS FROM FILE
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing users found.");
        }
    }

    // 🔐 HASH FUNCTION
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

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
