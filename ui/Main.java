package ui;

import java.util.Scanner;
import model.Student;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Sort Students by Name");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            // INPUT VALIDATION FOR MENU
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    // ADD STUDENT
                    int id;
                    while (true) {
                        try {
                            System.out.print("Enter ID: ");
                            id = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid ID! Enter number.");
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
                            System.out.println("Invalid Age! Enter number.");
                        }
                    }

                    service.addStudent(new Student(id, name, age));
                    break;

                case 2:
                    // VIEW
                    service.viewStudents();
                    break;

                case 3:
                    // DELETE
                    int deleteId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to delete: ");
                            deleteId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                        }
                    }
                    service.deleteStudent(deleteId);
                    break;

                case 4:
                    // SEARCH
                    int searchId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to search: ");
                            searchId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                        }
                    }
                    service.searchStudent(searchId);
                    break;

                case 5:
                    // UPDATE
                    int updateId;
                    while (true) {
                        try {
                            System.out.print("Enter ID to update: ");
                            updateId = Integer.parseInt(sc.nextLine());
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
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
                            System.out.println("Invalid input!");
                        }
                    }

                    service.updateStudent(updateId, newName, newAge);
                    break;

                case 6:
                    // SORT
                    service.sortStudentsByName();
                    break;

                case 7:
                    // EXIT
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
