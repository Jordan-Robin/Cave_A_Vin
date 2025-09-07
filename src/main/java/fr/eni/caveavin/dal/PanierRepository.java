package fr.eni.caveavin.dal;

import java.util.List;

import fr.eni.caveavin.bo.Client;
import fr.eni.caveavin.bo.Panier;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;


public interface PanierRepository extends JpaRepository<Panier, Integer> {

    @Query("SELECT p FROM Panier p WHERE p.client = :client AND p.numCommande = null")
    List<Panier> findPaniersWithJPQL(@Param("client") Client client);

    List<Panier> findByNumCommandeNullAndClient(@Param("client") Client client);

    @Query(
            value = "SELECT p.* FROM CAV_SHOPPING_CART p WHERE p.CLIENT_ID = :idClient AND p.ORDER_NUMBER IS NOT NULL",
            nativeQuery = true
    )
    List<Panier> findCommandesWithSQL(@Param("idClient")String idClient);

    List<Panier> findByNumCommandeNotNullAndClient(@Param("client") Client client);

}