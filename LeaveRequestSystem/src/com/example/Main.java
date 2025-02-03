package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Employee> employees = new ArrayList<>();
    private static List<LeaveRequest> leaveRequests = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add some initial employees
        employees.add(new Employee(1, "Sakshi", 10));
        employees.add(new Employee(2, "Lunatic", 15));
        employees.add(new Employee(3, "John", 20));

        // Menu loop for leave requests
        while (true) {
            System.out.println("\n1. Submit Leave Request");
            System.out.println("2. Process Leave Requests");
            System.out.println("3. Display Pending Requests");
            System.out.println("4. Generate Employee Report");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    submitLeaveRequest(scanner);
                    break;
                case 2:
                    processLeaveRequest(scanner);
                    break;
                case 3:
                    displayPendingRequests();
                    break;
                case 4:
                    generateEmployeeReport(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void submitLeaveRequest(Scanner scanner) {
        System.out.print("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter Leave Start Date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter Leave End Date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();
        System.out.print("Enter Reason for Leave: ");
        String reason = scanner.nextLine();

        // Check if employee exists
        Employee employee = findEmployeeById(employeeId);
        if (employee != null) {
            LeaveRequest leaveRequest = new LeaveRequest(employeeId, startDate, endDate, reason);
            leaveRequests.add(leaveRequest);
            System.out.println("Leave request submitted successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static void processLeaveRequest(Scanner scanner) {
        System.out.print("Enter Employee ID to process leave request: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Enter decision (Approve/Reject): ");
        String decision = scanner.nextLine().trim();

        // Find the leave request
        LeaveRequest leaveRequest = findLeaveRequestByEmployeeId(employeeId);
        if (leaveRequest != null) {
            leaveRequest.setStatus(decision);
            if (decision.equalsIgnoreCase("Approve")) {
                // Update leave balance if approved
                Employee employee = findEmployeeById(employeeId);
                if (employee != null) {
                    int leaveDays = leaveRequest.calculateLeaveDays();
                    int newBalance = employee.getLeaveBalance() - leaveDays;
                    employee.setLeaveBalance(newBalance);
                    System.out.println("Leave approved. " + leaveDays + " days deducted from leave balance.");
                }
            }
            System.out.println("Leave request status updated to " + decision);
        } else {
            System.out.println("Leave request not found for Employee ID " + employeeId);
        }
    }

    private static void displayPendingRequests() {
        System.out.println("\nPending Leave Requests:");
        for (LeaveRequest request : leaveRequests) {
            if (request.getStatus().equals("Pending")) {
                System.out.println("Employee ID: " + request.getEmployeeId() +
                        ", Start Date: " + request.getStartDate() +
                        ", End Date: " + request.getEndDate() +
                        ", Reason: " + request.getReason());
            }
        }
    }

    private static void generateEmployeeReport(Scanner scanner) {
        System.out.print("Enter Employee ID for report: ");
        int employeeId = scanner.nextInt();
        Employee employee = findEmployeeById(employeeId);

        if (employee != null) {
            System.out.println("\nLeave Report for " + employee.getName() + " (Employee ID: " + employeeId + "):");
            StringBuilder report = new StringBuilder();
            for (LeaveRequest request : leaveRequests) {
                if (request.getEmployeeId() == employeeId) {
                    report.append("Start Date: ").append(request.getStartDate())
                            .append(", End Date: ").append(request.getEndDate())
                            .append(", Reason: ").append(request.getReason())
                            .append(", Status: ").append(request.getStatus()).append("\n");
                }
            }
            if (report.length() == 0) {
                System.out.println("No leave requests found.");
            } else {
                System.out.println(report);
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private static Employee findEmployeeById(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getEmployeeId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    private static LeaveRequest findLeaveRequestByEmployeeId(int employeeId) {
        for (LeaveRequest request : leaveRequests) {
            if (request.getEmployeeId() == employeeId && request.getStatus().equals("Pending")) {
                return request;
            }
        }
        return null;
    }
}
