/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author brianjancarlos
 */
public class Role {

    private int roleId;
    private String roleName;
    private List<String> permissions;

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.permissions = assignPermissions(roleName);
    }

    private List<String> assignPermissions(String roleName) {
        switch (roleName) {
            case "HR":
                return Arrays.asList("VIEW_ALL_EMPLOYEES", "MANAGE_EMPLOYEES", "APPROVE_LEAVES", "VIEW_ALL_PAYROLL");
            case "Payroll Admin":
                return Arrays.asList("PROCESS_PAYROLL", "VIEW_ALL_PAYROLL");
            case "IT":
                return Arrays.asList("RESET_PASSWORDS", "MANAGE_ACCOUNTS");
            default:
                return Arrays.asList("VIEW_OWN_PAYROLL", "FILE_LEAVE");
        }
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}
