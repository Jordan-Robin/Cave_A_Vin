package fr.eni.caveavin.associations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

import fr.eni.caveavin.bo.Bouteille;
import fr.eni.caveavin.bo.Region;
import fr.eni.caveavin.dal.BouteilleRepository;
import fr.eni.caveavin.dal.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@Slf4j
@DataJpaTest
public class TestManyToOne {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    BouteilleRepository bouteilleRepository;

    @Autowired
    RegionRepository regionRepository;

    Region grandEst;
    Region paysDeLaLoire;
    Region nouvelleAquitaine;

    // Pas de cascade sur la relation ManyToOne dans Bouteille : il faut persister les régions avant les tests
    @BeforeEach
    public void setup() {
        paysDeLaLoire = regionRepository.save(
            Region
                .builder()
                .nom("Pays de la Loire")
                .build()
        );
        nouvelleAquitaine = regionRepository.save(
            Region
                .builder()
                .nom("Nouvelle Aquitaine")
                .build()
        );
    }

    @Test
    public void test_save() {
        final Bouteille bouteille = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2022")
                .prix(23.95f)
                .quantite(1298)
                .couleur(Bouteille.Couleur.BLANC)
                .build();

        // Association ManyToOne
        bouteille.setRegion(paysDeLaLoire);

        // Appel du comportement
        final Bouteille bouteilleDB = bouteilleRepository.save(bouteille);

        // Vérification de l'identifiant
        assertThat(bouteilleDB.getId()).isGreaterThan(0);

        // Vérification de l'association
        assertThat(bouteilleDB.getRegion()).isNotNull();
        assertThat(bouteilleDB.getRegion()).isEqualTo(paysDeLaLoire);
        log.info(bouteilleDB.toString());
    }

    @Test
    public void test_delete() {
        final Bouteille bouteille = Bouteille
                .builder()
                .nom("Blanc du DOMAINE ENI Ecole")
                .millesime("2022")
                .couleur(Bouteille.Couleur.ROSE)
                .prix(23.95f)
                .quantite(1298)
                .build();

        // Association ManyToOne
        bouteille.setRegion(nouvelleAquitaine);

        // Appel du comportement
        final Bouteille bouteilleDB = entityManager.persist(bouteille);
        entityManager.flush();

        // Vérification de l'identifiant
        assertThat(bouteilleDB.getId()).isGreaterThan(0);
        assertThat(bouteilleDB.getCouleur()).isNotNull();
        assertThat(bouteilleDB.getCouleur()).isEqualTo(Bouteille.Couleur.ROSE);
        assertThat(bouteilleDB.getRegion()).isNotNull();
        assertThat(bouteilleDB.getRegion()).isEqualTo(nouvelleAquitaine);

        // Appel du comportement
        bouteilleRepository.delete(bouteilleDB);

        // Vérification que l'entité a été supprimée
        final Bouteille bouteilleDB2 = entityManager.find(Bouteille.class, bouteilleDB.getId());
        assertNull(bouteilleDB2);

        // Vérifier que la région associée est toujours présente - PAS de cascade
        final List<Region> regions = regionRepository.findAll();
        assertThat(regions).isNotNull();
        assertThat(regions).isNotEmpty();
        assertThat(regions.size()).isEqualTo(2);
    }

}
