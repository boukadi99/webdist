package tn.esprit.spring.services;

import tn.esprit.spring.entities.Application;
import tn.esprit.spring.entities.ApplicationStatus;

import java.util.List;

public interface IApplicationService {
	Application addApplication(Application application);

	List<Application> listAllApplications();

	Application getApplicationById(Long id);

	Application updateApplication(Long id, Application application);

	void deleteApplication(Long id);

	Application updateApplicationStatus(Long id, ApplicationStatus status);
}