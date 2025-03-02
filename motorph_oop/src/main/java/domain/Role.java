/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import services.LoggerService;

/**
 *
 * @author brianjancarlos
 */
public class Role {

    private int roleId;
    private String roleName;

    //Constructor accepting roleId and roleName
    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public static String getRoleName(int roleId) {
        switch (roleId) {
            case 1:
                LoggerService.logInfo("Role ID " + roleId + " mapped to HR");
                return "HR";
            case 2:
                LoggerService.logInfo("Role ID " + roleId + " mapped to Payroll Admin");
                return "Payroll Admin";
            case 3:
                LoggerService.logInfo("Role ID " + roleId + " mapped to IT");
                return "IT";
            case 4:
                LoggerService.logInfo("Role ID " + roleId + " mapped to Employee");
                return "Employee";
            default:
                LoggerService.logWarning("Unknown role ID " + roleId);
                return "Unknown";
        }
    }
}
//    public Role(int roleId, String roleName) {
//        this.roleId = roleId;
//        this.roleName = roleName;
//    }
//
//    public int getRoleId() {
//        return roleId;
//    }
//
//    public String getRoleName() {
//        return roleName;
//    }

