package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<Adresse, Integer> {
}
