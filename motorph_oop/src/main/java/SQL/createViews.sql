/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  brianjancarlos
 * Created: 3 Mar 2025
 */

-- Shows basic employee information with job position and status.
CREATE VIEW employee_overview AS
SELECT 
    e.employee_id, 
    e.last_name, 
    e.first_name, 
    e.birthday, 
    e.address, 
    e.phone_number, 
    s.status_name AS employment_status, 
    p.position_name AS job_position, 
    e.supervisor_id
FROM Employee e
JOIN Employment_Statuses s ON e.status_id = s.status_id
JOIN Positions p ON e.position_id = p.position_id;


-- Displays employees with their salary and allowances.
CREATE VIEW compensation_summary AS
SELECT 
    e.employee_id, 
    e.last_name, 
    e.first_name, 
    c.basic_salary, 
    c.rice_subsidy, 
    c.phone_allowance, 
    c.clothing_allowance, 
    c.gross_semi_monthly_rate, 
    c.hourly_rate
FROM Employee e
JOIN Compensation_Details c ON e.compensation_id = c.compensation_id;

-- Shows employee attendance records with total work hours per day.
CREATE VIEW attendance_summary AS
SELECT 
    a.employee_id, 
    e.last_name, 
    e.first_name, 
    a.date, 
    a.login_time, 
    a.logout_time, 
    EXTRACT(EPOCH FROM (a.logout_time - a.login_time))/3600 AS total_hours
FROM Attendance a
JOIN Employee e ON a.employee_id = e.employee_id;

-- Displays payroll calculations for each employee.
CREATE VIEW payroll_report AS
SELECT 
    p.payroll_id, 
    e.employee_id, 
    e.last_name, 
    e.first_name, 
    p.payroll_period_start, 
    p.payroll_period_end, 
    p.hours_worked, 
    p.gross_pay, 
    p.deductions, 
    p.net_pay
FROM Payroll p
JOIN Employee e ON p.employee_id = e.employee_id;

-- Lists employees with their government-issued IDs.
CREATE VIEW government_ids_summary AS
SELECT 
    g.gov_id, 
    e.employee_id, 
    e.last_name, 
    e.first_name, 
    g.sss_number, 
    g.philhealth_number, 
    g.tin_number, 
    g.pagibig_number
FROM Government_IDs g
JOIN Employee e ON g.employee_id = e.employee_id;


-- Provides a quick reference for tax brackets.
CREATE VIEW withholding_tax_view AS
SELECT 
    tax_id, 
    monthly_rate, 
    base_tax, 
    tax_rate_percent, 
    plus_in_excess,
    CASE 
        WHEN tax_rate_percent = 0 THEN 'No withholding tax'
        ELSE CONCAT(base_tax, ' plus ', tax_rate_percent, '% in excess of ', plus_in_excess)
    END AS tax_rule
FROM Withholding_Tax;


-- Create UserRolesView View
CREATE VIEW UserRolesView AS
SELECT 
    u.employee_id, 
    e.first_name, 
    e.last_name, 
    r.role_name
FROM UserAccounts u
JOIN Employee e ON u.employee_id = e.employee_id
JOIN Roles r ON u.emp_role = r.role_id;

-- EmployeeDetailsView
CREATE VIEW EmployeeDetailsView AS
SELECT 
    e.employee_id, 
    e.first_name, 
    e.last_name, 
    e.birthday, 
    e.address, 
    e.phone_number, 
    s.status_name AS employment_status, 
    p.position_name AS job_position, 
    e.supervisor_id, 
    u.emp_role
FROM Employee e
JOIN Employment_Statuses s ON e.status_id = s.status_id
JOIN Positions p ON e.position_id = p.position_id
JOIN UserAccounts u ON e.employee_id = u.employee_id;

--- UPDATED VIEW
DROP VIEW IF EXISTS EmployeeDetailsView;

CREATE VIEW EmployeeDetailsView AS
SELECT 
    e.employee_id, 
    e.first_name, 
    e.last_name, 
    e.birthday, 
    e.address, 
    e.phone_number, 
    s.status_name AS employment_status, 
    p.position_name AS job_position, 
    e.supervisor_id, -- ✅ Keep this as an INT to avoid swapping issues
    COALESCE(sup.first_name || ' ' || sup.last_name, 'No Supervisor') AS supervisor_name, 
    u.emp_role AS role_id, 
    r.role_name AS emp_role
FROM Employee e
LEFT JOIN Employment_Statuses s ON e.status_id = s.status_id
LEFT JOIN Positions p ON e.position_id = p.position_id
LEFT JOIN Employee sup ON e.supervisor_id = sup.employee_id
LEFT JOIN UserAccounts u ON e.employee_id = u.employee_id
LEFT JOIN Roles r ON u.emp_role = r.role_id;

CREATE VIEW EmployeefullDetailsView AS
SELECT edv.employee_id,
    edv.first_name,
    edv.last_name,
    edv.birthday,
    edv.address,
    edv.phone_number,
    edv.employment_status,
    edv.job_position,
    edv.supervisor_id,
    edv.supervisor_name,
    edv.role_id,
    edv.emp_role,
    g.sss_number,
    g.philhealth_number,
    g.tin_number,
    g.pagibig_number,
    c.basic_salary,
    c.rice_subsidy,
    c.phone_allowance,
    c.clothing_allowance,
    c.gross_semi_monthly_rate,
    c.hourly_rate
   FROM employeedetailsview edv
     LEFT JOIN government_ids g ON edv.employee_id = g.employee_id
     LEFT JOIN compensation_details c ON edv.employee_id = c.employee_id;


--UserAccountView
DROP VIEW IF EXISTS UserAccountView;
CREATE VIEW UserAccountView AS
SELECT 
    ua.user_id,
    ua.employee_id,
    e.first_name,
    e.last_name,
    ua.emp_password
FROM useraccounts ua
JOIN employee e ON ua.employee_id = e.employee_id;