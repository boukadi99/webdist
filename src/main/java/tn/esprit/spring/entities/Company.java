package tn.esprit.spring.entities;

import javax.persistence.*; import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_employees")
    private int numberOfEmployees;

    @Column(name = "year_of_establishment")
    private LocalDate yearOfEstablishment;

    @Column(name = "average_rating", nullable = true)  // Allow null values
    private Double averageRating;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Internship> internships;  // A company can have multiple internships
}