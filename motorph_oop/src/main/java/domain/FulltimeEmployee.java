/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import services.PayrollCalculator;

/**
 *
 * @author brianjancarlos
 */
public class FulltimeEmployee extends Employee implements PayrollCalculator {

    public FulltimeEmployee(int employeeId, String firstName, String lastName, double basicSalary, Role role) {
        super(employeeId, firstName, lastName, basicSalary, role);
        
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

    public double getBasicSalary() {
        return basicSalary;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public double calculateSalary() {
        return basicSalary - calculateTax(basicSalary);
    }

    @Override
    public double calculateTax(double salary) {
        return salary * 0.15; // Example: 15% tax deduction
    }
}
