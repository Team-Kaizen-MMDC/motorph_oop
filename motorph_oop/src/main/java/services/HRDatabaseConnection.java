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
import domain.EmployeeManagement;

/**
 *
 * @author brianjancarlos
 */
public class HRDatabaseConnection implements EmployeeManagement {

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

    @Override
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
                    String status = resultSet.getString("employment_status");
                    String sss = resultSet.getString("sss_number");
                    String philhealth = resultSet.getString("philhealth_number");
                    String tin = resultSet.getString("tin_number");
                    String pagibig = resultSet.getString("pagibig_number");
                    String position = resultSet.getString("job_position");
                    String immediateSupervisorStr = resultSet.getString("supervisor_id");
                    String roleStr = resultSet.getString("role_id");
                    String basicsalaryStr = resultSet.getString("basic_salary");
                    String ricesubsidyStr = resultSet.getString("rice_subsidy");
                    String phoneAllowanceStr = resultSet.getString("phone_allowance");
                    String clothingAllowanceStr = resultSet.getString("clothing_allowance");
                    String grossSemiMonthlyRateStr = resultSet.getString("gross_semi_monthly_rate");
                    String hourlyRateStr = resultSet.getString("hourly_rate");

                    // Convert to appropriate types with null checks
                    int employeeId = (employeeIdStr != null) ? Integer.parseInt(employeeIdStr) : 0;
                    int supervisorId = (immediateSupervisorStr != null) ? Integer.parseInt(immediateSupervisorStr) : 0;
                    int roleId = (roleStr != null) ? Integer.parseInt(roleStr) : 0;
                    double basicSalary = (basicsalaryStr != null) ? Double.parseDouble(basicsalaryStr) : 0.0;
                    double riceSubsidy = (ricesubsidyStr != null) ? Double.parseDouble(ricesubsidyStr) : 0.0;
                    double phoneAllowance = (phoneAllowanceStr != null) ? Double.parseDouble(phoneAllowanceStr) : 0.0;
                    double clothingAllowance = (clothingAllowanceStr != null) ? Double.parseDouble(clothingAllowanceStr) : 0.0;
                    double grossSemiMonthlyRate = (grossSemiMonthlyRateStr != null) ? Double.parseDouble(grossSemiMonthlyRateStr) : 0.0;
                    double hourlyRate = (hourlyRateStr != null) ? Double.parseDouble(hourlyRateStr) : 0.0;

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
                LoggerService.logError("Error parsing numeric values: ", e);
                //System.err.println("Error parsing numeric values: " + e.getMessage());
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
