/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author nativ
 */
class Deductions {

 // SSS Calculation
public static double computeSSS(double salary) {
        if (salary <= 3250) return 135;
        if (salary >= 24750) return 1125;
        double base = Math.floor((salary - 3250) / 500) * 500 + 3250;
        return 135 + ((base - 3250) / 500) * 22.5;
    }

// Pagibig Calculation
    public static double computePagibig(double salary) {
        return Math.min(100, salary > 1500 ? salary * 0.02 : salary * 0.01);
    }

// Philhealth Calculation
    public static double computePhilhealth(double salary) {
        if (salary <= 10000) return 300 / 2;
        if (salary >= 60000) return 1800 / 2;
        return salary * 0.03 / 2;
    }

  // Compute Tax
    
    public static double computeTax(double taxableIncome) {
        if (taxableIncome <= 20832) return 0;
        if (taxableIncome <= 33333) return (taxableIncome - 20833) * 0.2;
        if (taxableIncome <= 66667) return (taxableIncome - 33333) * 0.25 + 2500;
        if (taxableIncome <= 166667) return (taxableIncome - 66667) * 0.3 + 10833;
        if (taxableIncome <= 666667) return (taxableIncome - 166667) * 0.32 + 40833.33;
        return (taxableIncome - 666667) * 0.35 + 200833.33;
    }
    
    
  // Total Deduction
    public float totalDeduction(float grossPay) {
    return (float) (computeSSS(grossPay) + computePagibig(grossPay) + computePhilhealth(grossPay));
}

  // total Deduction
    public static double computeTotalDeduction(double salary) {
        double totalDeductions = computeSSS(salary) + computePagibig(salary) + computePhilhealth(salary);
        return totalDeductions + computeTax(salary - totalDeductions);
    }
}
