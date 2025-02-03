package com.example;

public class LeaveRequest {
    private int employeeId;
    private String startDate;
    private String endDate;
    private String reason;
    private String status;

    public LeaveRequest(int employeeId, String startDate, String endDate, String reason) {
        this.employeeId = employeeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = "Pending";
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getReason() {
        return reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int calculateLeaveDays() {
        // Parse the dates (assumed format: YYYY-MM-DD)
        String[] startParts = startDate.split("-");
        String[] endParts = endDate.split("-");

        int startYear = Integer.parseInt(startParts[0]);
        int startMonth = Integer.parseInt(startParts[1]);
        int startDay = Integer.parseInt(startParts[2]);

        int endYear = Integer.parseInt(endParts[0]);
        int endMonth = Integer.parseInt(endParts[1]);
        int endDay = Integer.parseInt(endParts[2]);

        // Calculate the number of days between the start and end date (simplified logic)
        // You can replace this logic with a more complex approach if needed, using LocalDate

        int totalDays = (endYear - startYear) * 365 + (endMonth - startMonth) * 30 + (endDay - startDay);
        return Math.abs(totalDays);  // Return absolute days
    }
}
