-- Stored Procedures for CMS

DELIMITER //

DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor //
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN target_date DATE)
BEGIN
    SELECT d.name AS doctor_name, COUNT(a.id) AS total_appointments
    FROM doctor d
    LEFT JOIN appointment a ON d.id = a.doctor_id AND a.appointment_date = target_date
    GROUP BY d.id;
END //

DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth //
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN target_month INT, IN target_year INT)
BEGIN
    SELECT d.name AS doctor_name, COUNT(DISTINCT a.patient_id) AS total_patients
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE MONTH(a.appointment_date) = target_month AND YEAR(a.appointment_date) = target_year
    GROUP BY d.id
    ORDER BY total_patients DESC
    LIMIT 1;
END //

DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear //
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN target_year INT)
BEGIN
    SELECT d.name AS doctor_name, COUNT(DISTINCT a.patient_id) AS total_patients
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_date) = target_year
    GROUP BY d.id
    ORDER BY total_patients DESC
    LIMIT 1;
END //

DELIMITER ;
