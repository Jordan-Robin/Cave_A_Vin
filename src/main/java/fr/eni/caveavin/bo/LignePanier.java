package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "CAV_LINE")
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINE_ID")
    private Integer id;

    @Column(name = "QUANTITE")
    private int quantiteCommande;

    @Column(name = "PRICE", precision = 2)
    private float prix;

}
