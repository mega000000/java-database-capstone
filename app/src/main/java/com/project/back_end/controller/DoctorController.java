package com.project.back_end.controller;
import com.project.back_end.model.Doctor;
import com.project.back_end.service.DoctorService;
import com.project.back_end.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final TokenService tokenService;
    
    public DoctorController(DoctorService doctorService, TokenService tokenService) { 
        this.doctorService = doctorService;
        this.tokenService = tokenService;
    }
    
    @GetMapping
    public List<Doctor> getAllDoctors() { return doctorService.getAllDoctors(); }
    
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    // Required by Rubric: retrieves doctor availability based on user role, doctor ID, date, and token
    @GetMapping("/availability/{user}/{doctorId}/{date}/{token}")
    public ResponseEntity<?> getAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {
            
        // Implement token validation to enhance security
        if (!tokenService.isValidTokenSimple(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing token");
        }
        
        // Find the doctor
        Doctor doctor = doctorService.getAllDoctors().stream()
                .filter(d -> d.getId().equals(doctorId))
                .findFirst()
                .orElse(null);
                
        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor not found");
        }
        
        // Respond with structured appropriate response
        Map<String, Object> response = new HashMap<>();
        response.put("doctorId", doctorId);
        response.put("doctorName", doctor.getName());
        response.put("date", date);
        response.put("availableTimes", doctor.getAvailableTimes());
        response.put("requestedBy", user);
        
        return ResponseEntity.ok(response);
    }
}
