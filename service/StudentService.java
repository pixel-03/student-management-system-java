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
        for (Student s : students) {
            s.display();
        }
    }

    // DELETE
    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.id == id);
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
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // UPDATE ⭐
    public void updateStudent(int id, String name, int age) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = name;
                s.age = age;
                saveToFile();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // SAVE FILE ⭐
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.id + "," + s.name + "," + s.age);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error saving file");
        }
    }

    // LOAD FILE ⭐
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(
                        Integer.parseInt(data[0]),
                        data[1],
                        Integer.parseInt(data[2])
                ));
            }
        } catch (Exception e) {
            System.out.println("No previous data found");
        }
    }
}
