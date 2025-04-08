	package tn.esprit.spring.controllers;

	import lombok.AllArgsConstructor;
	import org.springframework.web.bind.annotation.*;

	import tn.esprit.spring.entities.Internship;
	import tn.esprit.spring.services.IInternshipService;

	import java.util.List;

	@RestController
	@AllArgsConstructor
	@RequestMapping("/internship")
	public class InternshipController {

		private final IInternshipService internshipService;

		// Add a new Internship (Stage)
		@PostMapping("/addInternship")
		public Internship addInternship(@RequestBody Internship internship) {
			// Debugging logs
			System.out.println("🔹 Received Internship: " + internship);
			System.out.println("🔹 Title: " + internship.getTitle());
			System.out.println("🔹 Company: " + internship.getCompany());
			System.out.println("🔹 Description: " + internship.getDescription());

			return internshipService.addInternship(internship);
		}


		// List all internships
		@GetMapping("/listInternships")
		public List<Internship> listInternships() {
			return internshipService.listAllInternships();
		}

		// Get an internship by ID
		@GetMapping("/getInternship/{id}")
		public Internship getInternship(@PathVariable Long id) {
			return internshipService.getInternshipById(id);
		}

		// Update an existing internship
		@PutMapping("/updateInternship/{id}")
		public Internship updateInternship(@RequestBody Internship internship, @PathVariable Long id) {
			return internshipService.updateInternship(id, internship);
		}

		// Delete an internship by ID
		@DeleteMapping("/deleteInternship/{id}")
		public void deleteInternship(@PathVariable Long id) {
			internshipService.deleteInternship(id);
		}
	}
