import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDAO studentDAO = new StudentDAO();
        boolean running = true;

        while (running) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Update student details");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[WARNING] Invalid input! Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Age: ");
                    int age;
                    try {
                        age = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("[WARNING] Invalid age format!");
                        break;
                    }
                    System.out.print("Enter Student Course: ");
                    String course = scanner.nextLine();

                    Student newStudent = new Student(name, age, course);
                    studentDAO.addStudent(newStudent);
                    break;

                case 2:
                    System.out.println("\n--- List of Students ---");
                    List<Student> students = studentDAO.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.printf("%-5s | %-20s | %-5s | %-20s%n", "ID", "Name", "Age", "Course");
                        System.out.println("----------------------------------------------------------");
                        for (Student s : students) {
                            System.out.printf("%-5d | %-20s | %-5d | %-20s%n", 
                                    s.getId(), s.getName(), s.getAge(), s.getCourse());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int updateId;
                    try {
                        updateId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("[WARNING] Invalid ID format!");
                        break;
                    }
                    
                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter New Age: ");
                    int newAge;
                    try {
                        newAge = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("[WARNING] Invalid age format!");
                        break;
                    }
                    System.out.print("Enter New Course: ");
                    String newCourse = scanner.nextLine();

                    Student updatedStudent = new Student(updateId, newName, newAge, newCourse);
                    studentDAO.updateStudent(updatedStudent);
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId;
                    try {
                        deleteId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("[WARNING] Invalid ID format!");
                        break;
                    }
                    studentDAO.deleteStudent(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting the application... Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("[WARNING] Invalid choice! Please select an option between 1 and 5.");
            }
        }
        scanner.close();
    }
}
