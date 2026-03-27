package service;

import model.Student;

import java.sql.*;

public class StudentService {

    // ADD STUDENT
    public void addStudent(Student s) {

        String checkSql = "SELECT id FROM students WHERE id = ?";
        String insertSql = "INSERT INTO students(id, name, age) VALUES(?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            // Check if ID exists
            checkStmt.setInt(1, s.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                System.out.println("Student ID already exists!");
                return;
            }

            // Insert
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

    // VIEW STUDENTS
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

    // DELETE STUDENT
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

    // SEARCH STUDENT
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

    // UPDATE STUDENT
    public void updateStudent(int id, String name, int age) {

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
