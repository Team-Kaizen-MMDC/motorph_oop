/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public interface LeaveApproval {

    void approveLeave(int leaveId);

    void rejectLeave(int leaveId);
    
    //void recordAttendance(int empID, java.sql.Date startDate, java.sql.Time defaultTimeIn, java.sql.Time defaultTimeOut); 

}
