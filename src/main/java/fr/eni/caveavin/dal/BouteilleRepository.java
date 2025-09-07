package fr.eni.caveavin.dal;

import java.util.List;

import fr.eni.caveavin.bo.Bouteille;
import fr.eni.caveavin.bo.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BouteilleRepository extends JpaRepository<Bouteille, Integer> {

    List<Bouteille> findByRegion(@Param("r") Region r);

    List<Bouteille> findByCouleur(@Param("c") Bouteille.Couleur c);

}
