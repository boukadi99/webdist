package tn.esprit.spring.services;

import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Rating;
import tn.esprit.spring.repositories.RatingRepository;
import tn.esprit.spring.entities.Company;
import tn.esprit.spring.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // Get all ratings for a specific company
    public List<Rating> getRatingsByCompany(String companyName) {
        return ratingRepository.findByCompanyName(companyName);
    }

    // Add a rating for a specific company and update the company's average rating
    public Rating addRating(String companyName, int rating) {
        Rating newRating = new Rating();
        newRating.setCompanyName(companyName);
        newRating.setRating(rating);  // Use the correct setter method
        Rating savedRating = ratingRepository.save(newRating);

        // After saving the new rating, we calculate the new average for the company
        updateCompanyAverageRating(companyName);

        return savedRating;
    }

    // Update the average rating of the company based on all its ratings
    private void updateCompanyAverageRating(String companyName) {
        List<Rating> ratings = ratingRepository.findByCompanyName(companyName);
        if (ratings.isEmpty()) return;

        double sum = 0;
        for (Rating r : ratings) {
            sum += r.getRating();
        }

        double averageRating = sum / ratings.size();

        // Update the company's average rating
        Company company = companyRepository.findByName(companyName);
        if (company != null) {
            company.setAverageRating(averageRating);
            companyRepository.save(company);
        }
    }
}
