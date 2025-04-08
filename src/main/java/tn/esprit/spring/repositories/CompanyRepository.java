package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	Company findByName(String name);  // Add this method to find a company by its name
}