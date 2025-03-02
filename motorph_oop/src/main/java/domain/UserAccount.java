/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public class UserAccount {

    private int userId;
    private int employeeId;
    private String empPassword;
    private int empRole; // Role ID (1 = HR, 2 = Payroll Admin, 3 = IT, 4 = Employee)

    public UserAccount(int userId, int employeeId, String empPassword, int empRole) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.empPassword = empPassword;
        this.empRole = empRole;
    }

    public int getUserId() {
        return userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public int getEmpRole() {
        return empRole;
    }

}
