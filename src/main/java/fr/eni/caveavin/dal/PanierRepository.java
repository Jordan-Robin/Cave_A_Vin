package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
}
