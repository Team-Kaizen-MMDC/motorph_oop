/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.HRAdmin;
import domain.Role;

/**
 *
 * @author brianjancarlos
 */
public class HRDatabaseConnection {

    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/motorph_oop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public boolean connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HRAdmin> getAllEmployeeDetails() {
        List<HRAdmin> employeeDetails = new ArrayList<>();
        if (connection != null) {
            String query = "SELECT * FROM employeefulldetailsview ORDER by employee_id";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                // Get all values as strings first
                String employeeIdStr = resultSet.getString("employee_id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone_number");
                String status = resultSet.getString("status");
                String sss = resultSet.getString("sss_number");
                String philhealth = resultSet.getString("philhealth_id");
                String tin = resultSet.getString("tin_id");
                String pagibig = resultSet.getString("pagibig_id");
                String position = resultSet.getString("position_name");
                String immediateSupervisorStr = resultSet.getString("immediate_supervisor");
                String roleStr = resultSet.getString("role_id");
                String basicsalaryStr = resultSet.getString("basic_salary");
                String ricesubsidyStr = resultSet.getString("rice_subsidy");
                String phoneAllowanceStr = resultSet.getString("phone_allowance");
                String clothingAllowanceStr = resultSet.getString("clothing_allowance");
                String grossSemiMonthlyRateStr = resultSet.getString("gross_semi_monthly_rate");
                String hourlyRateStr = resultSet.getString("hourly_rate");
                
                // Convert to appropriate types
                int employeeId = Integer.parseInt(employeeIdStr);
                int supervisorId = Integer.parseInt(immediateSupervisorStr);
                int roleId = Integer.parseInt(roleStr);
                double basicSalary = Double.parseDouble(basicsalaryStr);
                double riceSubsidy = Double.parseDouble(ricesubsidyStr);
                double phoneAllowance = Double.parseDouble(phoneAllowanceStr);
                double clothingAllowance = Double.parseDouble(clothingAllowanceStr);
                double grossSemiMonthlyRate = Double.parseDouble(grossSemiMonthlyRateStr);
                double hourlyRate = Double.parseDouble(hourlyRateStr);
                
                // Create Role object
                String roleName = Role.getRoleName(roleId);
                Role roleObj = new Role(roleId, roleName);
                
                // Create HRAdmin object with correctly typed parameters
                HRAdmin employeeDetail = new HRAdmin(
                    employeeId, 
                    firstName, 
                    lastName, 
                    birthday, 
                    address, 
                    phone, 
                    status, 
                    position,
                    supervisorId, 
                    roleObj, 
                    sss, 
                    philhealth, 
                    tin, 
                    pagibig, 
                    basicSalary, 
                    riceSubsidy, 
                    phoneAllowance, 
                    clothingAllowance,
                    grossSemiMonthlyRate, 
                    hourlyRate
                );
                
                employeeDetails.add(employeeDetail);
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.err.println("Error parsing numeric values: " + e.getMessage());
            e.printStackTrace();
        }
    }
    return employeeDetails;
}

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
