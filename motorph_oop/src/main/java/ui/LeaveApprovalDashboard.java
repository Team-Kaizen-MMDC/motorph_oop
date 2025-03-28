/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import domain.EmployeeID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.LoggerService;
import domain.LeaveRecords;
import java.sql.Date;
import java.sql.Time;
import services.DatabaseConnection;
import services.AttendanceService;

/**
 *
 * @author brianjancarlos
 */
public class LeaveApprovalDashboard extends javax.swing.JFrame {

    /**
     * Creates new form LeaveApprovalDashboard
     */
    public LeaveApprovalDashboard() {
        initComponents();
        setLocationRelativeTo(null);
        ResultSet rs = null;
        lbl_empid.setText(String.valueOf(EmployeeID.empid));
        listAllLeaves();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_leaveapproval = new javax.swing.JTable();
        btn_leaveapproval = new javax.swing.JButton();
        lbl_user = new javax.swing.JLabel();
        lbl_empid = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Leave Approval"));

        tbl_leaveapproval.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_leaveapproval);

        btn_leaveapproval.setText("Approve/Reject");
        btn_leaveapproval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_leaveapprovalActionPerformed(evt);
            }
        });

        lbl_user.setText("User:");

        lbl_empid.setText("empID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_leaveapproval, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_user)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_empid)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_user)
                    .addComponent(lbl_empid))
                .addGap(54, 54, 54)
                .addComponent(btn_leaveapproval, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_leaveapprovalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_leaveapprovalActionPerformed
        int selectedRow = tbl_leaveapproval.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a leave request to approve or reject.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Object[] options = {"Approve", "Reject", "Cancel"};
        int choice = JOptionPane.showOptionDialog(this, "Do you want to approve or reject the selected leave request?", "Approve/Reject Leave", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

        if (choice == JOptionPane.CANCEL_OPTION || choice == JOptionPane.CLOSED_OPTION) {
            // User cancelled or closed the dialog
            return;
        }

        String status = (choice == JOptionPane.YES_OPTION) ? "Approved" : "Rejected";
        int leaveId = (Integer) tbl_leaveapproval.getValueAt(selectedRow, 0); // Assuming leave_id is in the first column

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "UPDATE leave_requests SET status = ? WHERE leave_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, leaveId);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            // Refresh the table
            refreshTable();

            JOptionPane.showMessageDialog(this, "The leave request has been " + status.toLowerCase() + ".", "Request " + status, JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            String messageStatus = "Error updating leave request status";
            LoggerService.logError(messageStatus, ex);
            JOptionPane.showMessageDialog(this, messageStatus, "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        int empID = Integer.parseInt(tbl_leaveapproval.getValueAt(selectedRow, 1).toString());
        String fname = tbl_leaveapproval.getValueAt(selectedRow, 2).toString();
        String lname = tbl_leaveapproval.getValueAt(selectedRow, 3).toString();
        Date startDate = Date.valueOf(tbl_leaveapproval.getValueAt(selectedRow, 5).toString());
        Time defaultTimeIn = Time.valueOf("08:00:00");
        Time defaultTimeOut = Time.valueOf("17:00:00");

        if ("Approved".equals(status)) {

            AttendanceService.recordAttendance(empID, startDate, defaultTimeIn, defaultTimeOut);
            LoggerService.logInfo("Leave recorded in Attenance table : " + empID + " | " + startDate);
        } else if ("Rejected".equals(status)) {
            AttendanceService.deleteRecord(empID, startDate);
            LoggerService.logInfo("Leave deleted in Attenance table : " + empID + " | " + startDate);
        }
    }//GEN-LAST:event_btn_leaveapprovalActionPerformed

    public void refreshTable() {
        listAllLeaves();
        LoggerService.logInfo("Table Refreshed");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LeaveApprovalDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LeaveApprovalDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LeaveApprovalDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LeaveApprovalDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LeaveApprovalDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_leaveapproval;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_empid;
    private javax.swing.JLabel lbl_user;
    private javax.swing.JTable tbl_leaveapproval;
    // End of variables declaration//GEN-END:variables

    public void listAllLeaves() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM leave_requests ORDER BY leave_id DESC";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) tbl_leaveapproval.getModel();
            model.setRowCount(0); // Clear existing data
            model.setColumnIdentifiers(new String[]{"Leave ID", "Employee ID", "Name", "Surname",
                "Leave_Type", "Start Date", "End Date", "Remarks", "Status"});

            while (rs.next()) {
                LeaveRecords record = new LeaveRecords(
                        rs.getInt("leave_id"),
                        rs.getString("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("leave_type"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getString("remarks"),
                        rs.getString("status")
                );

                Object[] row = new Object[]{
                    record.getLeaveId(),
                    record.getEmployee_id(),
                    record.getFirstName(),
                    record.getLastName(),
                    record.getLeaveStart(),
                    record.getLeaveEnd(),
                    record.getLeaveType(),
                    record.getRemarks(),
                    record.getStatus()
                };
                model.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
