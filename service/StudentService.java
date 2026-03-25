package service;

import model.Student;
import java.sql.*;
import java.util.*;

public class StudentService {

    // ADD
    public void addStudent(Student s) {
        String sql = "INSERT INTO students(id, name, age) VALUES(?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, s.getId());
            stmt.setString(2, s.getName());
            stmt.setInt(3, s.getAge());

            stmt.executeUpdate();
            System.out.println("Student added!");

        } catch (Exception e) {
            System.out.println("Error adding student!");
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

        } catch (Exception e) {
            System.out.println("Error fetching students!");
        }
    }

    // DELETE
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Student deleted!");

        } catch (Exception e) {
            System.out.println("Error deleting student!");
        }
    }

    // SEARCH
    public void searchStudent(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.printf("%-10d %-20s %-5d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"));
            } else {
                System.out.println("Student not found!");
            }

        } catch (Exception e) {
            System.out.println("Error searching student!");
        }
    }

    // UPDATE
    public void updateStudent(int id, String name, int age) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";

        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.setInt(3, id);

            stmt.executeUpdate();
            System.out.println("Student updated!");

        } catch (Exception e) {
            System.out.println("Error updating student!");
        }
    }
}
