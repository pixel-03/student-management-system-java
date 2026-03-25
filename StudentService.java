import java.util.*;
import java.io.*;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public StudentService() {
        loadFromFile();
    }

    // ADD
    public void addStudent(int id, String name, int age) {
        students.add(new Student(id, name, age));
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

    // SEARCH 🔥
    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // UPDATE 🔥
    public void updateStudent(int id, String newName, int newAge) {
        for (Student s : students) {
            if (s.id == id) {
                s.name = newName;
                s.age = newAge;
                saveToFile();
                System.out.println("Student updated!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // SAVE
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(s.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }

    // LOAD
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}
