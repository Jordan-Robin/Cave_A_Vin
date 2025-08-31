package fr.eni.caveavin.bo;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "CAV_SHOPPING_CART")
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOPPING_CART_ID")
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "ORDER_NUMBER", length = 200)
    private String numCommande;

    @Column(name = "TOTAL_PRICE", precision = 2)
    private float prixTotal;

    @Column(name = "PAID")
    private boolean paye;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "SHOPPING_CART_ID")
    private @Builder.Default List<LignePanier> lignesPanier = new ArrayList<>();

}
