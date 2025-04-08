package tn.esprit.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.entities.Internship;

import java.util.List;

public interface IInternshipRepository extends JpaRepository<Internship, Long> {
    // Custom query to fetch distinct company names
    @Query("SELECT DISTINCT i.company FROM Internship i")
    List<String> findDistinctCompanies();
}