package ui;

import service.StudentService;
import service.Database;
import service.AuthService;
import model.Student;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // 🔥 Initialize database
        Database.init();

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        System.out.println("===== LOGIN SYSTEM =====");

        boolean isLoggedIn = false;

        // 🔐 LOGIN / REGISTER LOOP
        while (!isLoggedIn) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Choose: ");

            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                System.out.print("Username: ");
                String username = sc.nextLine();

                System.out.print("Password: ");
                String password = sc.nextLine();

                if (auth.login(username, password)) {
                    System.out.println("Login successful!");
                    isLoggedIn = true;
                } else {
                    System.out.println("Invalid credentials!");
                }

            } else if (option == 2) {
                System.out.print("New Username: ");
                String username = sc.nextLine();

                System.out.print("New Password: ");
                String password = sc.nextLine();

                auth.register(username, password);
            } else {
                System.out.println("Invalid option!");
            }
        }

        // 🔥 AFTER LOGIN → MAIN SYSTEM
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student (by ID)");
            System.out.println("5. Update Student");
            System.out.println("6. Search by Name");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    service.addStudent(new Student(id, name, age));
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to delete: ");
                    int deleteId = sc.nextInt();
                    service.deleteStudent(deleteId);
                    break;

                case 4:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    service.searchStudent(searchId);
                    break;

                case 5:
                    System.out.print("Enter ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Age: ");
                    int newAge = sc.nextInt();

                    service.updateStudent(updateId, newName, newAge);
                    break;

                case 6:
                    sc.nextLine();
                    System.out.print("Enter name: ");
                    String nameSearch = sc.nextLine();
                    service.searchStudentByName(nameSearch);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
