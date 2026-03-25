package service;

import model.Student;
import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    // ADD STUDENT
    public void addStudent(Student s) {
        students.add(s);
        System.out.println("Student added successfully!");
    }

    // VIEW ALL STUDENTS
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found!");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.id == id);

        if (removed) {
            System.out.println("Student deleted!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // SEARCH STUDENT
    public void searchStudent(int id) {
        for (Student s : students) {
            if (s.id == id) {
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }
}
