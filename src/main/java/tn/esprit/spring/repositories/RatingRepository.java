package tn.esprit.spring.repositories;

import tn.esprit.spring.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findByCompanyName(String companyName);  // Use String instead of Company entity
}
