package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entities.Application;
import tn.esprit.spring.entities.ApplicationStatus;
import tn.esprit.spring.entities.Internship;
import tn.esprit.spring.services.IApplicationService;
import tn.esprit.spring.repositories.IInternshipRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/application")
public class ApplicationController {

    private final IApplicationService applicationService;
    private final IInternshipRepository internshipRepository;

    private static final String UPLOAD_DIR = "C:/Users/DHIA/Desktop/spring/examen-assurance/uploads/";

    static {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    // Add a new Application (Candidature)
    @PostMapping("/addApplication")
    public Application addApplication(
            @RequestParam("email") String email,
            @RequestParam("studentId") String studentId,
            @RequestParam("stageId") String stageId,
            @RequestParam("cv") MultipartFile cv) throws IOException {

        Long studentIdLong = Long.parseLong(studentId);
        Long stageIdLong = Long.parseLong(stageId);

        // Validate file
        if (cv.isEmpty()) {
            return null; // Handle the case where no file is uploaded
        }

        // Generate a unique file name
        String fileName = studentIdLong + "_" + cv.getOriginalFilename();
        File dest = new File(UPLOAD_DIR + fileName);

        // Ensure the directory exists before writing the file
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs(); // Create the parent directories if needed
        }

        // Transfer the file to the specified location
        cv.transferTo(dest);

        // Fetch the internship (stage) by ID
        Internship internship = internshipRepository.findById(stageIdLong).orElse(null);
        if (internship == null) {
            throw new RuntimeException("Internship not found");
        }

        // Create the application object
        Application application = new Application();
        application.setEmail(email);
        application.setStudentId(studentIdLong);
        application.setCv(fileName); // Save the file name or path
        application.setInternship(internship); // Set the internship object

        // Set the default status to PENDING
        application.setStatus(ApplicationStatus.PENDING);

        // Call the service to add the application
        return applicationService.addApplication(application);
    }

    // List all applications
    @GetMapping("/listApplications")
    public List<Application> listApplications() {
        return applicationService.listAllApplications();
    }

    // Get an application by ID
    @GetMapping("/getApplication/{id}")
    public Application getApplication(@PathVariable Long id) {
        return applicationService.getApplicationById(id);
    }

    // Update an application (e.g., change status)
    @PutMapping("/updateApplication/{id}")
    public Application updateApplication(@RequestBody Application application, @PathVariable Long id) {
        return applicationService.updateApplication(id, application);
    }

    // Delete an application by ID
    @DeleteMapping("/deleteApplication/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

    // Change the status of an application (e.g., Pending -> Accepted)
    @PutMapping("/updateStatus/{id}")
    public Application updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        return applicationService.updateApplicationStatus(id, status);
    }

    // Upload CV for a given application
    @PostMapping("/uploadCv/{applicationId}")
    public String uploadCv(@PathVariable Long applicationId, @RequestParam("file") MultipartFile file) throws IOException {
        // Validate file
        if (file.isEmpty()) {
            return "No file uploaded.";
        }

        // Generate a unique file name
        String fileName = applicationId + "_" + file.getOriginalFilename();

        // Save the file to the upload directory
        File dest = new File(UPLOAD_DIR + fileName);

        // Ensure the directory exists before saving the file
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs(); // Create directories if they don't exist
        }

        // Transfer the file to the specified location
        file.transferTo(dest);

        // Update the application entity with the file name or path
        Application application = applicationService.getApplicationById(applicationId);
        application.setCv(fileName); // Assuming "cv" is the attribute in your Application entity
        applicationService.updateApplication(applicationId, application);

        return "File uploaded successfully.";
    }

    // Serve the uploaded CV file (for downloading/viewing)
    @GetMapping("/cv/{fileName}")
    public ResponseEntity<FileSystemResource> downloadCv(@PathVariable String fileName) {
        File file = new File(UPLOAD_DIR + fileName);

        // Check if the file exists
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 if the file doesn't exist
        }

        FileSystemResource resource = new FileSystemResource(file);

        // Set the content disposition to make it downloadable
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}