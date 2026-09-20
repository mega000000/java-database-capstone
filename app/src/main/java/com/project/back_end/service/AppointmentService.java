package com.project.back_end.service;
import com.project.back_end.model.Appointment;
import com.project.back_end.dto.AppointmentDto;
import com.project.back_end.repository.AppointmentRepository;
import com.project.back_end.repository.DoctorRepository;
import com.project.back_end.repository.PatientRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    
    public List<Appointment> getAllAppointments() { return appointmentRepository.findAll(); }
    
    public Appointment scheduleAppointment(AppointmentDto dto) {
        Appointment appt = new Appointment();
        appt.setDoctor(doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found")));
        appt.setPatient(patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found")));
        appt.setAppointmentDate(dto.getAppointmentDate());
        appt.setAppointmentTime(dto.getAppointmentTime());
        appt.setStatus("SCHEDULED");
        return appointmentRepository.save(appt);
    }
}
