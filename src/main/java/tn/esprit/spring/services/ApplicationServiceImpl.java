package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Application;
import tn.esprit.spring.entities.ApplicationStatus;
import tn.esprit.spring.repositories.IApplicationRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationServiceImpl implements IApplicationService {

    private final IApplicationRepository applicationRepository;

    @Override
    public Application addApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public List<Application> listAllApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public Application updateApplication(Long id, Application application) {
        Application existingApplication = getApplicationById(id);
        existingApplication.setInternship(application.getInternship());

        // Set studentId (updated from candidate)
        existingApplication.setStudentId(application.getStudentId()); // Set studentId here instead of candidate

        existingApplication.setStatus(application.getStatus());
        existingApplication.setEmail(application.getEmail()); // Update email


        // Save the updated application
        return applicationRepository.save(existingApplication);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public Application updateApplicationStatus(Long id, ApplicationStatus status) {
        Application existingApplication = getApplicationById(id);
        existingApplication.setStatus(status);
        return applicationRepository.save(existingApplication);
    }
}