/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  brianjancarlos
 * Created: 3 Mar 2025
 */

-- Connect to the database
\c motorph_oop;

-- Import Employee Data
COPY Employee(employee_id, last_name, first_name, birthday, address, phone_number, status_id, position_id)
FROM '/Users/brianjancarlos/Downloads/employee_details.csv' 
DELIMITER ',' CSV HEADER;

-- Updated Import Employee Data
COPY Employee(employee_id, last_name, first_name, birthday, address, phone_number, status_id, position_id, supervisor_id, compensation_id)
FROM '/Users/brianjancarlos/Downloads/employee_final.csv' 
DELIMITER ',' CSV HEADER;

-- Import Attendance Data
COPY Attendance(attendance_id, employee_id, date, login_time, logout_time)
FROM '/Users/brianjancarlos/Downloads/attendance_updated_new.csv' 
DELIMITER ',' CSV HEADER;

COPY Withholding_Tax(tax_id, monthly_rate, base_tax, tax_rate_percent, plus_in_excess)
FROM '/Users/brianjancarlos/Downloads/withholding_tax_reference.csv' 
DELIMITER ',' CSV HEADER;

COPY Positions(position_id, position_name)
FROM '/Users/brianjancarlos/Downloads/positions.csv' 
DELIMITER ',' CSV HEADER;

COPY Employment_Statuses(status_id, status_name)
FROM '/Users/brianjancarlos/Downloads/employment_statuses.csv' 
DELIMITER ',' CSV HEADER;

COPY Government_IDs(gov_id, employee_id, sss_number, philhealth_number, tin_number, pagibig_number)
FROM '/Users/brianjancarlos/Downloads/government_ids.csv' 
DELIMITER ',' CSV HEADER;


COPY Compensation_Details(compensation_id, employee_id, effective_date, basic_salary, rice_subsidy, phone_allowance, clothing_allowance, gross_semi_monthly_rate, hourly_rate)
FROM '/Users/brianjancarlos/Downloads/compensation_details.csv' 
DELIMITER ',' CSV HEADER;


COPY UserAccounts(employee_id, emp_password, emp_role)
FROM '/Users/brianjancarlos/Downloads/login_pwd.csv'
DELIMITER ',' 
CSV HEADER;