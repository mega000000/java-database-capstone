-- Sample Data Insertion

-- Insert Admins
INSERT IGNORE INTO admin (username, password) VALUES ('admin_super', 'hashed_pass_123');

-- Insert Doctors
INSERT IGNORE INTO doctor (name, specialty, available_times) VALUES ('Dr. Alice Smith', 'Cardiology', '09:00-17:00');
INSERT IGNORE INTO doctor (name, specialty, available_times) VALUES ('Dr. Bob Jones', 'General Practice', '10:00-18:00');

-- Insert Patients
INSERT IGNORE INTO patient (name, email, address) VALUES ('John Doe', 'john.doe@example.com', '123 Main St');
INSERT IGNORE INTO patient (name, email, address) VALUES ('Jane Roe', 'jane.roe@example.com', '456 Oak St');

-- Insert Appointments (Assuming doctor IDs 1 and 2, patient IDs 1 and 2 exist)
INSERT IGNORE INTO appointment (doctor_id, patient_id, appointment_date, appointment_time, status) VALUES (1, 1, '2023-10-15', '09:30:00', 'COMPLETED');
INSERT IGNORE INTO appointment (doctor_id, patient_id, appointment_date, appointment_time, status) VALUES (1, 2, '2023-10-15', '11:00:00', 'SCHEDULED');
INSERT IGNORE INTO appointment (doctor_id, patient_id, appointment_date, appointment_time, status) VALUES (2, 1, '2023-11-20', '14:00:00', 'COMPLETED');
