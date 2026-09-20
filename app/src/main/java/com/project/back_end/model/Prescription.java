package com.project.back_end.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    private Long appointmentId;
    
    private String patientName;
    
    private List<String> medication;
    
    private String notes;

    public Prescription() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public List<String> getMedication() { return medication; }
    public void setMedication(List<String> medication) { this.medication = medication; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
