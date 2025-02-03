package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Create an array to store Employee objects
        System.out.println("Enter the number of employees:");
        int numEmployees = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Employee[] employees = new Employee[numEmployees];

        // Step 2: Input employee details and record attendance
        for (int i = 0; i < numEmployees; i++) {
            System.out.println("\nEnter details for Employee " + (i + 1));
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Name: ");
            String name = scanner.nextLine();

            // Create Employee object
            employees[i] = new Employee(id, name);

            // Record attendance
            System.out.print("Attendance (Present/Absent): ");
            String attendance = scanner.nextLine();
            employees[i].setAttendanceStatus(attendance);
        }

        // Menu for additional options
        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Display All Attendance Records");
            System.out.println("2. Search Attendance by Employee ID");
            System.out.println("3. Generate Attendance Report");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Display all attendance records
                    System.out.println("\nAttendance Records:");
                    for (Employee employee : employees) {
                        employee.displayEmployeeDetails();
                    }
                    break;

                case 2:
                    // Search for an employee's attendance record by ID
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    searchAttendance(employees, searchId);
                    break;

                case 3:
                    // Generate attendance report
                    generateAttendanceReport(employees);
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }

    // Search attendance by employee ID
    private static void searchAttendance(Employee[] employees, int id) {
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == id) {
                System.out.println("\nAttendance Record:");
                employee.displayEmployeeDetails();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Employee with ID " + id + " not found!");
        }
    }

    // Generate an attendance report
    private static void generateAttendanceReport(Employee[] employees) {
        int presentCount = 0, absentCount = 0;

        for (Employee employee : employees) {
            if ("Present".equalsIgnoreCase(employee.getAttendanceStatus())) {
                presentCount++;
            } else if ("Absent".equalsIgnoreCase(employee.getAttendanceStatus())) {
                absentCount++;
            }
        }

        // Display report
        System.out.println("\nAttendance Report:");
        System.out.println("Total Employees: " + employees.length);
        System.out.println("Present: " + presentCount);
        System.out.println("Absent: " + absentCount);
    }
}
