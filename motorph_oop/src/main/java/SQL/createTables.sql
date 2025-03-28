/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  brianjancarlos
 * Created: 3 Mar 2025
 */



DROP TABLE IF EXISTS Employee CASCADE;

CREATE TABLE Employee (
    employee_id SERIAL PRIMARY KEY,           -- Auto-incrementing primary key
    last_name VARCHAR(50) NOT NULL,           -- Last name
    first_name VARCHAR(50) NOT NULL,          -- First name
    birthday DATE NOT NULL,                   -- Birth date
    address TEXT NOT NULL,                    -- Address
    phone_number VARCHAR(15) UNIQUE NOT NULL, -- Unique phone number
    status_id INT NOT NULL,                   -- Employment status reference
    position_id INT NOT NULL,                 -- Job position reference
    supervisor_id INT,                         -- Reference to another employee (supervisor)
    compensation_id INT,                       -- Reference to compensation details
    role_id INT,                               -- Employee role
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Foreign Key Constraints
    CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES Employment_Statuses(status_id) ON DELETE CASCADE,
    CONSTRAINT fk_position FOREIGN KEY (position_id) REFERENCES Positions(position_id) ON DELETE CASCADE,
    CONSTRAINT fk_supervisor FOREIGN KEY (supervisor_id) REFERENCES Employee(employee_id) ON DELETE SET NULL,
    CONSTRAINT fk_compensation FOREIGN KEY (compensation_id) REFERENCES Compensation_Details(compensation_id) ON DELETE SET NULL,
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES Roles(role_id) ON DELETE SET NULL
);

-- Create Employment Statuses Table
CREATE TABLE Employment_Statuses (
    status_id INT PRIMARY KEY,
    status_name VARCHAR(50)
);

-- Create Positions Table
CREATE TABLE Positions (
    position_id INT PRIMARY KEY,
    position_name VARCHAR(50)
);

-- Create Payroll Table
CREATE TABLE Payroll (
    payroll_id INT PRIMARY KEY,
    employee_id INT,
    payroll_period_start DATE,
    payroll_period_end DATE,
    hours_worked DECIMAL(6,2),
    gross_pay DECIMAL(10,2),
    deductions DECIMAL(10,2),
    net_pay DECIMAL(10,2),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- Create Government IDs Table
CREATE TABLE Government_IDs (
    gov_id INT PRIMARY KEY,
    employee_id INT,
    sss_number VARCHAR(20),
    philhealth_number VARCHAR(20),
    tin_number VARCHAR(20),
    pagibig_number VARCHAR(20),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- Create Compensation Details Table
CREATE TABLE Compensation_Details (
    compensation_id INT PRIMARY KEY,
    employee_id INT,
    effective_date DATE,
    basic_salary DECIMAL(10,2),
    rice_subsidy DECIMAL(10,2),
    phone_allowance DECIMAL(10,2),
    clothing_allowance DECIMAL(10,2),
    gross_semi_monthly_rate DECIMAL(10,2),
    hourly_rate DECIMAL(10,2),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- Create Attendance Table
CREATE TABLE attendance (
    attendance_id INT PRIMARY KEY,
    employee_id INT,
    date DATE,
    login_time TIMESTAMP,
    logout_time TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

-- UPDATE Attendance Table
CREATE SEQUENCE attendance_attendance_id_seq;
ALTER TABLE attendance 
ALTER COLUMN attendance_id SET DEFAULT nextval('attendance_attendance_id_seq');

-- First create a sequence if it doesn't exist
CREATE SEQUENCE IF NOT EXISTS attendance_id_seq;

-- Then alter the column to use the sequence
ALTER TABLE attendance 
  ALTER COLUMN attendance_id SET DEFAULT nextval('attendance_id_seq');

-- Optional: Set the sequence to start after your highest ID
SELECT setval('attendance_id_seq', (SELECT MAX(attendance_id) FROM attendance));


-- Import Withholding Tax Data (If you need a specific table, create one before importing)
DROP TABLE IF EXISTS Withholding_Tax CASCADE;
CREATE TABLE Withholding_Tax (
    tax_id SERIAL PRIMARY KEY,
    monthly_rate VARCHAR(50) NOT NULL,
    base_tax DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    tax_rate_percent INT NOT NULL DEFAULT 0,
    plus_in_excess DECIMAL(10,2) NOT NULL DEFAULT 0
);

-- for user login and password
CREATE TABLE UserAccounts (
    user_id SERIAL PRIMARY KEY,
    employee_id INT UNIQUE NOT NULL,
    emp_password VARCHAR(255) NOT NULL,
    emp_role INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id) ON DELETE CASCADE
);

-- Create Roles Table
CREATE TABLE Roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL UNIQUE
);

-- Insert Default Roles
INSERT INTO Roles (role_name) VALUES ('HR'), ('Payroll Admin'), ('IT');

-- Add role_id to Employee Table
ALTER TABLE Employee ADD COLUMN role_id INT;
ALTER TABLE Employee ADD CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES Roles(role_id);
