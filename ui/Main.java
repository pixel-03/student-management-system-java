package ui;

import java.util.Scanner;
import model.Student;
import service.StudentService;

public class Main {

    // ANSI COLORS
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {

            System.out.println(CYAN + "\n======================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("======================================" + RESET);
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Sort Students");
            System.out.println("7. Exit");
            System.out.print(YELLOW + "Enter your choice: " + RESET);

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println(RED + "Invalid input!" + RESET);
                continue;
            }

            switch (choice) {

                case 1:
                    int id;
                    while (true) {
                        try {
                            System.out.print("Enter ID: ");
                            id = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid ID!" + RESET);
                        }
                    }

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    int age;
                    while (true) {
                        try {
                            System.out.print("Enter Age: ");
                            age = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid Age!" + RESET);
                        }
                    }

                    service.addStudent(new Student(id, name, age));
                    break;

                case 2:
                    service.viewStudents();
                    break;

                case 3:
                    int deleteId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to delete: ");
                            deleteId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input!" + RESET);
                        }
                    }
                    service.deleteStudent(deleteId);
                    break;

                case 4:
                    int searchId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to search: ");
                            searchId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input!" + RESET);
                        }
                    }
                    service.searchStudent(searchId);
                    break;

                case 5:
                    int updateId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to update: ");
                            updateId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input!" + RESET);
                        }
                    }

                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();

                    int newAge;
                    while (true) {
                        try {
                            System.out.print("Enter new Age: ");
                            newAge = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input!" + RESET);
                        }
                    }

                    service.updateStudent(updateId, newName, newAge);
                    break;

                case 6:
                    service.sortStudentsByName();
                    break;

                case 7:
                    System.out.println(GREEN + "Exiting system..." + RESET);
                    return;

                default:
                    System.out.println(RED + "Invalid choice!" + RESET);
            }

            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
    }
}
