package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Internship;
import tn.esprit.spring.entities.Application;
import tn.esprit.spring.repositories.IInternshipRepository;
import tn.esprit.spring.repositories.IApplicationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class InternshipServiceImpl implements IInternshipService {

	private final IInternshipRepository internshipRepository;
	private final IApplicationRepository applicationRepository;

	@Override
	public Internship addInternship(Internship internship) {
		return internshipRepository.save(internship);
	}

	@Override
	public List<Internship> listAllInternships() {
		return internshipRepository.findAll();
	}

	@Override
	public Internship getInternshipById(Long id) {
		return internshipRepository.findById(id).orElse(null);
	}

	@Override
	public Internship updateInternship(Long id, Internship internship) {
		if (internshipRepository.existsById(id)) {
			internship.setId(id);
			return internshipRepository.save(internship);
		}
		return null;
	}

	@Override
	public void deleteInternship(Long id) {
		internshipRepository.deleteById(id);
	}

	@Override
	public Application addApplicationToInternship(Long internshipId, Application application) {
		Internship internship = internshipRepository.findById(internshipId).orElse(null);
		if (internship != null) {
			application.setInternship(internship);
			return applicationRepository.save(application);
		}
		throw new RuntimeException("Internship not found!");
	}

	@Override
	public List<Application> listApplicationsByInternship(Long internshipId) {
		return applicationRepository.findByInternshipId(internshipId);
	}

	@Override
	public List<Application> listApplicationsByStatus(String status) {
		return applicationRepository.findByStatus(status);
	}
}
