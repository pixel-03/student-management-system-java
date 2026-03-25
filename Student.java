public class Student {
    int id;
    String name;
    int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Convert object to file format
    public String toFileString() {
        return id + "," + name + "," + age;
    }

    // Convert file data back to object
    public static Student fromFileString(String line) {
        String[] data = line.split(",");
        return new Student(
            Integer.parseInt(data[0]),
            data[1],
            Integer.parseInt(data[2])
        );
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
    }
}
