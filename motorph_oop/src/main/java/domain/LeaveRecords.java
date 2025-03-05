/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public class LeaveRecords {

    private int leaveId;
    private String employee_id;
    private String firstName;
    private String lastName;
    private String leaveStart;
    private String leaveEnd;
    private String leaveType;
    private String remarks;
    private String status;

    //Contructs
    public LeaveRecords(int leaveId, String employee_id, String firstName, String lastName, String leaveStart, String leaveEnd, String leaveType, String remarks, String status) {
        this.leaveId = leaveId;
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.leaveStart = leaveStart;
        this.leaveEnd = leaveEnd;
        this.leaveType = leaveType;
        this.remarks = remarks;
        this.status = status;
    }

    //getters and setters
    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(String leaveStart) {
        this.leaveStart = leaveStart;
    }

    public String getLeaveEnd() {
        return leaveEnd;
    }

    public void setLeaveEnd(String leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
