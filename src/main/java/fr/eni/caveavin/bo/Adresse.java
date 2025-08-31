package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "CAV_ADRESSES")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer id;

    @Column(name = "STREET", nullable = false, length = 255)
    private String rue;

    @Column(name = "POSTAL_CODE", nullable = false, length = 5)
    private String codePostal;

    @Column(name = "CITY", nullable = false, length = 100)
    private String ville;
}
