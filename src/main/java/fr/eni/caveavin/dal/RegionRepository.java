package fr.eni.caveavin.dal;

import fr.eni.caveavin.bo.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
