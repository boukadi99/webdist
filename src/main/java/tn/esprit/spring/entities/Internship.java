package tn.esprit.spring.entities;

import javax.persistence.*; import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Internship {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;  // Title of the internship
	private String description;  // Description of the internship
	private String company;  // Company offering the internship
	private String location;  // Location of the internship

	private LocalDate startDate;  // Start date
	private LocalDate endDate;  // End date
}