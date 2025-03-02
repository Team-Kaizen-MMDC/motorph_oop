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
    protected double basicSalary;
    protected Role role;

    public Employee(int employeeId, String firstName, String lastName, double basicSalary, Role role) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.basicSalary = basicSalary;
        this.role = role;
    }

    public abstract double calculateSalary();

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Role getRole() {
        return role;
    }
}
