/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public class Role {

    private int roleId;
    private String roleName;

    public static String getRoleName(int roleId) {
        switch (roleId) {
            case 1:
                return "HR";
            case 2:
                return "Payroll Admin";
            case 3:
                return "IT";
            case 4:
                return "Employee";
            default:
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

