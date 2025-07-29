package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
