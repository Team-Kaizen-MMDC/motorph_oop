/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public abstract class Employee {

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

    public Employee(int employeeId, String firstName, String lastName, String birthday,
            String address, String phoneNumber, String employmentStatus,
            String jobPosition, int supervisorId, Role role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.employmentStatus = employmentStatus;
        this.jobPosition = jobPosition;
        this.supervisorId = supervisorId;
        this.role = role;
    }

    // Ensure salary calculation is required in subclasses
    public abstract double calculateSalary();

    //  method for full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Role getRole() {
        return role;
    }

    public abstract void displayEmployeeDetails();

    public int getEmployeeId() {
        return employeeId;
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
//    protected int employeeId;
//    protected String firstName;
//    protected String lastName;
//    protected double basicSalary;
//    protected Role role;
//
//    public Employee(int employeeId, String firstName, String lastName, double basicSalary, Role role) {
//        this.employeeId = employeeId;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.basicSalary = basicSalary;
//        this.role = role;
//    }
//
//    public abstract double calculateSalary();
//
//    public String getFullName() {
//        return firstName + " " + lastName;
//    }
//
//    public Role getRole() {
//        return role;
//    }
}
