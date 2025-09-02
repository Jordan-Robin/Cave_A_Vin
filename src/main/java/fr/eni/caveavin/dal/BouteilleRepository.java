package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Bouteille;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {
}
