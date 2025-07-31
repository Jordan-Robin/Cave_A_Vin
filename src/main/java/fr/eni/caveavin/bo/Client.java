package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CAV_CLIENTS")
public class Client {
    // TODO contraintes validation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @EqualsAndHashCode.Include
    @Column(name = "LOGIN", nullable = false, unique = true, length = 100)
    private String pseudo;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String nom;

    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String prenom;

    @ToString.Exclude
    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;

}
