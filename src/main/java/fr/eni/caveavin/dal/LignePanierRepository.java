package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignePanierRepository extends JpaRepository<LignePanier, Integer> {
}
