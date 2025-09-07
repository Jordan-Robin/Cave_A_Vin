package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByPseudo(@Param("pseudo") String pseudo);

    Utilisateur findByPseudoAndPassword(@Param("pseudo") String pseudo, @Param("password") String password);

}
