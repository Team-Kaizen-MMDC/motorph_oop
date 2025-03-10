/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.HRAdmin;

/**
 *
 * @author brianjancarlos
 */
public class EmployeeDatabaseService {

//    public List<HRAdmin> getAllEmployeeDetails() {
//        List<HRAdmin> employeeDetails = new ArrayList<>();
//
//            String query = "SELECT * FROM employee ORDER by employee_id";
//            try {
//                PreparedStatement pstmt = connection.prepareStatement(query);
//                ResultSet resultSet = pstmt.executeQuery();
//
//                while (resultSet.next()) {
//                    String employeeId = resultSet.getString("employee_id"); // these are the column names in the employee_details db
//                    String lastName = resultSet.getString("last_name");
//                    String firstName = resultSet.getString("first_name");
//                    String birthday = resultSet.getString("birthday");
//                    String address = resultSet.getString("address");
//                    String phone = resultSet.getString("phone_number");
//                    String status = resultSet.getString("status");
//                    String sss = resultSet.getString("sss_id");
//                    String philhealth = resultSet.getString("philhealth_id");
//                    String tin = resultSet.getString("tin_id");
//                    String pagibig = resultSet.getString("pagibig_id");
//                    String position = resultSet.getString("position_name");
//                    String immediateSupervisor = resultSet.getString("immediate_supervisor");
//                    //String basicsalary = resultSet.getString("basic_salary");
//                    //String ricesubsidy = resultSet.getString("rice_subsidy");
//                    HRAdmin employeeDetail = new HRAdmin(employeeId, lastName, firstName, birthday, address,
//                            phone, status, sss, philhealth, tin, pagibig, position, immediateSupervisor);
//                    employeeDetails.add(employeeDetail);
//                }
//
//                resultSet.close();
//                pstmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return employeeDetails;
//    }
    
    
}
