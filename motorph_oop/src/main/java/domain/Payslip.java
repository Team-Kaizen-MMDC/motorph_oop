/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public class Payslip {

    private int employeeId;
    private String periodStart;
    private String periodEnd;
    private double grossPay;
    private double deductions;
    private double netPay;
    private double allowances;
    private double totalHoursWorked;

    public Payslip(int employeeId, String periodStart, String periodEnd,
            double totalHoursWorked,
            double grossPay, double allowances, double deductions, double netPay) {
        this.employeeId = employeeId;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalHoursWorked = totalHoursWorked;
        this.grossPay = grossPay;
        this.allowances = allowances;
        this.deductions = deductions;
        this.netPay = netPay;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public double getAllowances() {
        return allowances;
    }

    public double getTotalHoursWorked() {
        return totalHoursWorked;
    }

    // Output formatted payslip (Philippine Peso)
    @Override
    public String toString() {
        return "=== Payslip for Employee ID: " + employeeId + " ===\n"
                + "Pay Period: " + periodStart + " to " + periodEnd + "\n"
                + "Total Hours Worked: " + totalHoursWorked + "\n"
                + "Gross Pay: ₱" + String.format("%.2f", grossPay) + "\n"
                + "Allowances: ₱" + String.format("%.2f", allowances) + "\n"
                + "Deductions: ₱" + String.format("%.2f", deductions) + "\n"
                + "Net Pay: ₱" + String.format("%.2f", netPay) + "\n"
                + "==========================";
    }

    // Optional: Check for zero-pay periods
    public boolean isZeroPay() {
        return grossPay == 0 && netPay == 0;
    }
}
