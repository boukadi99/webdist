package tn.esprit.spring.services;

import tn.esprit.spring.entities.Internship;
import tn.esprit.spring.entities.Application;

import java.util.List;

public interface IInternshipService {
	// Add an internship (stage)
	Internship addInternship(Internship internship);

	// List all internships
	List<Internship> listAllInternships();

	// Get an internship by ID
	Internship getInternshipById(Long id);

	// Update an internship
	Internship updateInternship(Long id, Internship internship);

	// Delete an internship
	void deleteInternship(Long id);

	// Add an application (candidature) to an internship
	Application addApplicationToInternship(Long internshipId, Application application);

	// List all applications for a specific internship
	List<Application> listApplicationsByInternship(Long internshipId);

	// List applications by status
	List<Application> listApplicationsByStatus(String status);
}
