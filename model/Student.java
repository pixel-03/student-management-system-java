package model;

public class Student {

    private int id;
    private String name;
    private int age;

    // Constructor
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Setters (for update)
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Convert to file format
    public String toFileString() {
        return id + "," + name + "," + age;
    }

    // Convert from file to object
    public static Student fromFileString(String line) {
        String[] data = line.split(",");
        return new Student(
                Integer.parseInt(data[0]),
                data[1],
                Integer.parseInt(data[2])
        );
    }

    // Display (table format)
    public void display() {
        System.out.printf("%-10d %-20s %-5d\n", id, name, age);
    }
}
