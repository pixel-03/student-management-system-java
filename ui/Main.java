package ui;

import java.util.Scanner;
import model.Student;
import service.StudentService;
import service.AuthService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.println("===== LOGIN SYSTEM =====");

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (!auth.login(username, password)) {
            System.out.println("Invalid credentials! Access denied.");
            return;
        }

        System.out.println("Login successful!\n");

        StudentService service = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input!");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    service.addStudent(new Student(id, name, age));
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    service.deleteStudent(Integer.parseInt(sc.nextLine()));
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    service.searchStudent(Integer.parseInt(sc.nextLine()));
                    break;

                case 5:
                    System.out.print("Enter ID: ");
                    int uid = Integer.parseInt(sc.nextLine());

                    System.out.print("New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("New Age: ");
                    int newAge = Integer.parseInt(sc.nextLine());

                    service.updateStudent(uid, newName, newAge);
                    break;

                case 6:
                    service.sortStudentsByName();
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
