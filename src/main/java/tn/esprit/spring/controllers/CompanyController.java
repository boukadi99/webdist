package tn.esprit.spring.controllers;

import tn.esprit.spring.entities.Company;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.services.CompanyService;
import tn.esprit.spring.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")

public class CompanyController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CompanyService companyService;

    // Fetch all companies
    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Fetch ratings for a company
    @GetMapping("/{companyName}/ratings")
    public List<Rating> getCompanyRatings(@PathVariable String companyName) {
        return ratingService.getRatingsByCompany(companyName);
    }

    // Add a rating for a company
    @PostMapping("/{companyName}/rate")
    public Rating addRating(@PathVariable String companyName, @RequestParam int rating) {
        System.out.println("Received company name: " + companyName); // Log the company name
        return ratingService.addRating(companyName, rating);
    }


    // Fetch distinct company names from internships
    @GetMapping("/fetch-from-internships")
    public List<String> fetchDistinctCompanyNames() {
        return companyService.getAllCompaniesFromInternships();
    }

    // Add a new company
    @PostMapping("/add")
    public Company addCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }
}