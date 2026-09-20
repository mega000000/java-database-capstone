package com.project.back_end.repository;
import com.project.back_end.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
