package ui;

import service.*;
import model.Student;

import java.util.Scanner;
import java.io.Console;

public class Main {

    public static void main(String[] args) {

        Database.init();

        Scanner sc = new Scanner(System.in);
        AuthService auth = new AuthService();

        String role = null;

        System.out.println("===== LOGIN SYSTEM =====");

        while (role == null) {

            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Choose: ");

            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                System.out.print("Username: ");
                String username = sc.nextLine();

                // 🔐 PASSWORD MASKING
                Console console = System.console();
                String password;

                if (console != null) {
                    char[] pwd = console.readPassword("Password: ");
                    password = new String(pwd);
                } else {
                    System.out.print("Password: ");
                    password = sc.nextLine();
                }

                role = auth.login(username, password);

                if (role != null) {
                    System.out.println("Login successful! Role: " + role);
                } else {
                    System.out.println("Invalid credentials!");
                }

            } else if (option == 2) {
                System.out.print("New Username: ");
                String username = sc.nextLine();

                // 🔐 PASSWORD MASKING (REGISTER)
                Console console = System.console();
                String password;

                if (console != null) {
                    char[] pwd = console.readPassword("New Password: ");
                    password = new String(pwd);
                } else {
                    System.out.print("New Password: ");
                    password = sc.nextLine();
                }

                System.out.print("Enter role (admin/user): ");
                String newRole = sc.nextLine().toLowerCase();

                auth.register(username, password, newRole);
            } else {
                System.out.println("Invalid option!");
            }
        }

        StudentService service = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");

            if (role.equals("admin")) {
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Delete Student");
                System.out.println("4. Search Student (by ID)");
                System.out.println("5. Update Student");
                System.out.println("6. Search by Name");
                System.out.println("7. Exit");
            } else {
                System.out.println("1. View Students");
                System.out.println("2. Search Student (by ID)");
                System.out.println("3. Search by Name");
                System.out.println("4. Exit");
            }

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (role.equals("admin")) {

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
                        System.out.print("Enter ID: ");
                        service.deleteStudent(sc.nextInt());
                        break;

                    case 4:
                        System.out.print("Enter ID: ");
                        service.searchStudent(sc.nextInt());
                        break;

                    case 5:
                        System.out.print("Enter ID: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("New Name: ");
                        String newName = sc.nextLine();

                        System.out.print("New Age: ");
                        int newAge = sc.nextInt();

                        service.updateStudent(uid, newName, newAge);
                        break;

                    case 6:
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        service.searchStudentByName(sc.nextLine());
                        break;

                    case 7:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }

            } else {

                switch (choice) {
                    case 1:
                        service.viewStudents();
                        break;

                    case 2:
                        System.out.print("Enter ID: ");
                        service.searchStudent(sc.nextInt());
                        break;

                    case 3:
                        sc.nextLine();
                        System.out.print("Enter name: ");
                        service.searchStudentByName(sc.nextLine());
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice!");
                }
            }
        }
    }
}                        
