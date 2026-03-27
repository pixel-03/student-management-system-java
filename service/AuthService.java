package service;

import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class AuthService {

    private HashMap<String, String[]> users = new HashMap<>();
    private final String FILE_NAME = "users.txt";

    public AuthService() {
        loadFromFile();
    }

    // 🔐 LOGIN (returns role)
    public String login(String username, String password) {
        if (users.containsKey(username)) {
            String[] data = users.get(username);
            String storedHash = data[0];
            String role = data[1];

            String inputHash = hashPassword(password);

            if (storedHash.equals(inputHash)) {
                return role; // 👈 return role
            }
        }
        return null;
    }

    // 📝 REGISTER
    public void register(String username, String password, String role) {
        if (users.containsKey(username)) {
            System.out.println("User already exists!");
        } else {
            users.put(username, new String[]{hashPassword(password), role});
            saveToFile();
            System.out.println("User registered successfully!");
        }
    }

    // 💾 SAVE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String[]> entry : users.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue()[0] + "," + entry.getValue()[1]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users!");
        }
    }

    // 📂 LOAD
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    users.put(parts[0], new String[]{parts[1], parts[2]});
                }
            }
        } catch (IOException e) {
            System.out.println("No existing users found.");
        }
    }

    // 🔐 HASH
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
