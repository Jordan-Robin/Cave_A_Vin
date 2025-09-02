package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"region"})
@Builder
@Entity
@Table(name = "CAV_BOTTLE")
public class Bouteille {

    public enum Couleur {
        ROUGE,
        BLANC,
        ROSE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOTTLE_ID")
    private Integer id;

    @Column(name = "NAME", length = 250, nullable = false, unique = true)
    private String nom;

    @Column(name = "SPARKLING")
    private boolean petillant;

    @Column(name = "VINTAGE", length = 100)
    private String millesime;

    @Column(name = "QUANTITY")
    private int quantite;

    @Column(name = "PRICE", precision = 2)
    private float prix;

    @Enumerated(EnumType.STRING)
    @Column(name = "COLOR", length = 10)
    private Couleur couleur;

    // Pas de cascade : la création d'une bouteille n'entrainera pas la création d'une région en base
    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

}
