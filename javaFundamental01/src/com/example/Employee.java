package com.example;

import java.time.LocalDate;

public class Employee {
    private int employeeId;
    private String name;
    private String attendanceStatus;
    private String attendanceDate;

    // Constructor
    public Employee(int employeeId, String name) {
        this.employeeId = employeeId;
        this.name = name;
        this.attendanceStatus = "Absent"; // Default attendance status
        this.attendanceDate = LocalDate.now().toString(); // Set current date
    }

    // Getter and Setter methods
    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    // Display employee details
    public void displayEmployeeDetails() {
        System.out.println("ID: " + employeeId + ", Name: " + name +
                ", Attendance: " + attendanceStatus + ", Date: " + attendanceDate);
    }
}
