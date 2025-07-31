package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByPseudo(String pseudo);

}
