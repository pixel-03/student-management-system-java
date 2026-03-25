package service;

import model.Student;
import java.util.*;
import java.io.*;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentService() {
        loadFromFile();
    }

    // ADD
    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("Student added!");
    }

    // VIEW
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        System.out.println("\n-------------------------------------------");
        System.out.printf("%-10s %-20s %-5s\n", "ID", "NAME", "AGE");
        System.out.println("-------------------------------------------");

        for (Student s : students) {
            s.display();
        }

        System.out.println("-------------------------------------------");
    }

    // DELETE
    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (removed) {
            saveToFile();
            System.out.println("Student deleted!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // SEARCH
    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("\nStudent Found:");
                System.out.println("-------------------------------------------");
                System.out.printf("%-10s %-20s %-5s\n", "ID", "NAME", "AGE");
                System.out.println("-------------------------------------------");
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // UPDATE
    public void updateStudent(int id, String name, int age) {
        for (Student s : students) {
            if (s.getId() == id) {
                s.setName(name);
                s.setAge(age);
                saveToFile();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // SORT
    public void sortStudentsByName() {
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Students sorted by name!");
    }

    // SAVE FILE
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving file!");
        }
    }

    // LOAD FILE
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}
