package tn.esprit.spring.entities;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long studentId;  // Placeholder for student ID

	@ManyToOne
	private Internship internship;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	@Column(name = "email")  // Add email field
	private String email;    // Add email attribute

	@Column(name = "cv")
	private String cv;  // This will store the file path or URL
	private Long stageId; // Add this field

}