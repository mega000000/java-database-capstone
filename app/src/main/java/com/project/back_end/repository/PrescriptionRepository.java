package com.project.back_end.repository;
import com.project.back_end.model.Prescription;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface PrescriptionRepository extends MongoRepository<Prescription, String> {}
