import java.util.ArrayList;
import java.util.Scanner;

// Class to represent each student
class Student {
    String name;
    double grade;

    // Constructor
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeTracker {

    public static void main(String[] args) {
        Scanner Scanner = new Scanner(System.in);

        // List to store Student objects
        ArrayList<Student> students = new ArrayList<>();

        System.out.println("=== Student Grade Tracker ===");

        // Ask how many students
        System.out.print("Enter the number of students: ");
        int numberOfStudents = Scanner.nextInt();
        Scanner.nextLine(); // consume newline character

        // Input student data
        for (int i = 1; i <= numberOfStudents; i++) {
            System.out.println("\nEnter details for Student " + i + ":");

            // Get name
            System.out.print("Name: ");
            String name = Scanner.nextLine();

            // Get grade (with basic validation)
            double grade = -1;
            while (grade < 0 || grade > 100) {
                System.out.print("Grade (0 - 100): ");
                grade = Scanner.nextDouble();
                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade! Please enter a value between 0 and 100.");
                }
            }
            Scanner.nextLine(); // consume newline

            // Add student to list
            students.add(new Student(name, grade));
        }

        // If no students, exit
        if (students.isEmpty()) {
            System.out.println("No students entered. Exiting program.");
            return;
        }

        // Calculate average, highest, lowest
        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;

        for (Student s : students) {
            total += s.grade;
            if (s.grade > highest) {
                highest = s.grade;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
            }
        }

        double average = total / students.size();

        // Display report
        System.out.println("\n=== Summary Report ===");
        System.out.printf("%-20s %-10s%n", "Name", "Grade");
        System.out.println("------------------------------");
        for (Student s : students) {
            System.out.printf("%-20s %-10.2f%n", s.name, s.grade);
        }
        System.out.println("------------------------------");
        System.out.printf("Average Grade : %.2f%n", average);
        System.out.printf("Highest Grade : %.2f%n", highest);
        System.out.printf("Lowest Grade  : %.2f%n", lowest);
    }
}

