/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.Timestamp;

/**
 *
 * @author brianjancarlos
 */
public interface AttendanceTracker {

    void timeIn();

    void timeOut();

    boolean isTimedIn();
}
