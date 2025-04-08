package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Application;

import java.util.List;

public interface IApplicationRepository extends JpaRepository<Application, Long> {
	// Find applications by status
	List<Application> findByStatus(String status);

	// Find applications by internship ID
	List<Application> findByInternshipId(Long internshipId);
}