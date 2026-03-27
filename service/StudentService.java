package service;

import model.Student;

import java.sql.*;

public class StudentService {

    // ADD STUDENT
    public void addStudent(Student s) {

        // ✅ VALIDATION
        if (s.getId() <= 0) {
            System.out.println("Invalid ID!");
            return;
        }

        if (s.getName().trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        if (s.getAge() <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        String checkSql = "SELECT id FROM students WHERE id = ?";
        String insertSql = "INSERT INTO students(id, name, age) VALUES(?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            // Check duplicate ID
            checkStmt.setInt(1, s.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student ID already exists!");
                return;
            }

            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setInt(1, s.getId());
            insertStmt.setString(2, s.getName());
            insertStmt.setInt(3, s.getAge());

            insertStmt.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // VIEW
    public void viewStudents() {

        String sql = "SELECT * FROM students";

        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n-------------------------------------------");
            System.out.printf("%-10s %-20s %-5s\n", "ID", "NAME", "AGE");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            }

            System.out.println("-------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // SEARCH BY ID
    public void searchStudent(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\nStudent Found:");
                System.out.printf("%-10d %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // 🔥 NEW: SEARCH BY NAME
    public void searchStudentByName(String name) {

        String sql = "SELECT * FROM students WHERE name LIKE ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + name + "%");

            ResultSet rs = stmt.executeQuery();

            boolean found = false;

            System.out.println("\nSearch Results:");
            System.out.println("-------------------------------------------");
            System.out.printf("%-10s %-20s %-5s\n", "ID", "NAME", "AGE");
            System.out.println("-------------------------------------------");

            while (rs.next()) {
                found = true;
                System.out.printf("%-10d %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            }

            if (!found) {
                System.out.println("No students found!");
            }

            System.out.println("-------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // UPDATE
    public void updateStudent(int id, String name, int age) {

        if (name.trim().isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        if (age <= 0) {
            System.out.println("Invalid age!");
            return;
        }

        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setInt(3, id);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated!");
            } else {
                System.out.println("Student not found!");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
