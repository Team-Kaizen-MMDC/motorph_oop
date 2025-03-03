/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Timestamp;
import services.AttendanceService;
import services.LoggerService;

/**
 *
 * @author brianjancarlos
 */
public abstract class Employee implements UserAction, AttendanceTracker {

    protected int employeeId;
    protected String firstName;
    protected String lastName;
    protected String birthday;
    protected String address;
    protected String phoneNumber;
    protected String employmentStatus;
    protected String jobPosition;
    protected int supervisorId;
    protected Role role;
    protected double basicSalary;
    protected double riceSubsidy;
    protected double phoneAllowance;
    protected double clothingAllowance;
    protected double grossSemiMonthlyRate;
    protected double hourlyRate;
    protected String sssNumber;
    protected String philhealthNumber;
    protected String tinNumber;
    protected String pagibigNumber;
    private boolean isTimedIn;

    public Employee(int employeeId, String firstName, String lastName, String birthday,
            String address, String phoneNumber, String employmentStatus,
            String jobPosition, int supervisorId, Role role, double basicSalary, double riceSubsidy, double phoneAllowance, double clothingAllowance,
            double grossSemiMonthlyRate, double hourlyRate,
            String sssNumber, String philhealthNumber, String tinNumber, String pagibigNumber) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.employmentStatus = employmentStatus;
        this.jobPosition = jobPosition;
        this.role = role;
        this.basicSalary = basicSalary;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
        this.grossSemiMonthlyRate = grossSemiMonthlyRate;
        this.hourlyRate = hourlyRate;
        this.sssNumber = sssNumber;
        this.philhealthNumber = philhealthNumber;
        this.tinNumber = tinNumber;
        this.pagibigNumber = pagibigNumber;
        this.isTimedIn = false;
    }

    // Getters
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public Role getRole() {
        return role;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getRiceSubsidy() {
        return riceSubsidy;
    }

    public double getPhoneAllowance() {
        return phoneAllowance;
    }

    public double getClothingAllowance() {
        return clothingAllowance;
    }

    public double getGrossSemiMonthlyRate() {
        return grossSemiMonthlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilhealthNumber() {
        return philhealthNumber;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public String getPagibigNumber() {
        return pagibigNumber;
    }

    public boolean isIsTimedIn() {
        return isTimedIn;
    }

   

    // Payroll Calculation
    public double calculateSalary() {
        return basicSalary - calculateTax(basicSalary);
    }

    public double calculateTax(double salary) {
        return salary * 0.15;
    }

    // Implement AttendanceTracker Methods
    @Override
    public void timeIn() {
        if (!isTimedIn) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            AttendanceService.logTimeIn(employeeId, timestamp);
            isTimedIn = true;
            LoggerService.logInfo("Employee ID " + employeeId + " has timed in at " + timestamp);
        } else {
            LoggerService.logWarning("Employee ID " + employeeId + " already timed in.");
        }
    }

    @Override
    public void timeOut() {
        if (isTimedIn) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            AttendanceService.logTimeOut(employeeId, timestamp);
            isTimedIn = false;
            LoggerService.logInfo("Employee ID " + employeeId + " has timed out at " + timestamp);
        } else {
            LoggerService.logWarning("Employee ID " + employeeId + " cannot time out before timing in.");
        }
    }

    @Override
    public boolean isTimedIn() {
        return isTimedIn;
    }

}
