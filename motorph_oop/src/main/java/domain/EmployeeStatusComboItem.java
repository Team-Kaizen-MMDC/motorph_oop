/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

/**
 *
 * @author brianjancarlos
 */
public class EmployeeStatusComboItem {

    private String key; // status_name
    private int value;  // status_id

    public EmployeeStatusComboItem(String key, int value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key; // Display status_name in JComboBox
    }

    public int getValue() {
        return value; // Get status_id when needed
    }
}
