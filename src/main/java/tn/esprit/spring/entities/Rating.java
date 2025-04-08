package tn.esprit.spring.entities;

import javax.persistence.*; import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;  // Store company name instead of linking to Company entity

    private int rating;  // Rating value

}
