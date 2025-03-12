/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import domain.Employee;
import domain.Role;

/**
 *
 * @author nativ
 */
public class Payroll {
      private Employee employee;  // Payroll has an Employee
    private double totalHours;
    private double grossPay;
    private double totalDeductions;
    private double netPay;

    public Payroll(Employee employee, double totalHours) {
        this.employee = employee;
        this.totalHours = totalHours;
        this.grossPay = calculateGrossPay();
        this.totalDeductions = Deductions.computeTotalDeduction(grossPay);
        this.netPay = grossPay - totalDeductions;
    }

    private double calculateGrossPay() {
        return (employee.getHourlyRate() * totalHours) + CompensationDetails.computeTotalAllowances(employee);
    }

    public void displayPayroll() {
        System.out.println("Payroll for: " + employee.getFullName());
        System.out.println("Gross Pay: " + grossPay);
        System.out.println("Total Deductions: " + totalDeductions);
        System.out.println("Net Pay: " + netPay);
    }
}