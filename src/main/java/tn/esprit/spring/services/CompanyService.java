package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Company;
import tn.esprit.spring.entities.Internship;
import tn.esprit.spring.repositories.IInternshipRepository;
import tn.esprit.spring.repositories.CompanyRepository;
import tn.esprit.spring.repositories.RatingRepository;
import tn.esprit.spring.entities.Rating;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private IInternshipRepository internshipRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RatingRepository ratingRepository;

    // Fetch all companies
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Fetch distinct company names from internships
    public List<String> getAllCompaniesFromInternships() {
        return internshipRepository.findDistinctCompanies();
    }

    // Add a new company
    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    // Fetch ratings for a company
    public List<Rating> getRatingsForCompany(String companyName) {
        return ratingRepository.findByCompanyName(companyName);
    }
}