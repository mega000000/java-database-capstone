package com.project.back_end.repository;
import com.project.back_end.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
