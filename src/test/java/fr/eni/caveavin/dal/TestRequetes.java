package fr.eni.caveavin.dal;

import static org.assertj.core.api.Assertions.assertThat;

import fr.eni.caveavin.bo.Bouteille;
import fr.eni.caveavin.bo.Region;
import fr.eni.caveavin.bo.Utilisateur;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;

import java.util.ArrayList;
import java.util.List;


@DataJpaTest
public class TestRequetes {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    BouteilleRepository bouteilleRepository;

    Region paysDeLaLoire;

    List<Bouteille> bouteilles;

    @BeforeEach
    void initDB() {
        jeuDeDonneesBouteilles();
        jeuDeDonneesUtilisateur();
    }

    private void jeuDeDonneesBouteilles() {

        final Region grandEst = Region.builder().nom("Grand Est").build();
        final Region bourgogne = Region.builder().nom("Bourgogne").build();
        final Region paysDeLaLoire = Region.builder().nom("Pays de la Loire").build();

        entityManager.persist(grandEst);
        entityManager.persist(bourgogne);
        entityManager.persist(paysDeLaLoire);
        entityManager.flush();

        bouteilles = new ArrayList<>();
        bouteilles.add(Bouteille
                               .builder()
                               .nom("Riesling")
                               .millesime("2018")
                               .petillant(false)
                               .prix(12.5f)
                               .quantite(10)
                               .couleur(Bouteille.Couleur.BLANC)
                               .region(grandEst).build()
                      );
        bouteilles.add(Bouteille
                               .builder()
                               .nom("Gewurztraminer")
                               .millesime("2019")
                               .petillant(false)
                               .prix(15.0f)
                               .quantite(5)
                               .couleur(Bouteille.Couleur.BLANC)
                               .region(grandEst).build()
                      );
        bouteilles.add(Bouteille
                               .builder()
                               .nom("Chablis")
                               .millesime("2020")
                               .petillant(false)
                               .prix(20.0f)
                               .quantite(8)
                               .couleur(Bouteille.Couleur.BLANC)
                               .region(bourgogne).build()
                      );

        bouteilles.forEach(b -> {
            entityManager.persist(b);
            assertThat(b.getId()).isGreaterThan(0);
        });
        entityManager.flush();

    }

    private void jeuDeDonneesUtilisateur() {

        final List<Utilisateur> utilisateurs = new ArrayList<>();
        utilisateurs.add(
            Utilisateur
                .builder()
                .pseudo("harrsionfor@email.fr")
                .password("Acteur&Producteur")
                .nom("Ford")
                .prenom("Harrison")
                .build()
        );

        utilisateurs.add(
            Utilisateur
                .builder()
                .pseudo("georgeslucas@email.fr")
                .password("Réalisateur&Producteur")
                .nom("Lucas")
                .prenom("George")
                .build()
        );

        utilisateurs.forEach(u -> {
            entityManager.persist(u);
            assertThat(u.getId()).isGreaterThan(0);
        });
        entityManager.flush();
    }

    @Test
    public void test_BouteilleRepository_findByRegion() {
        final List<Bouteille> listeBouteilleDev = bouteilleRepository.findByRegion(paysDeLaLoire);

        assertThat(listeBouteilleDev).isNotNull();
        assertThat(listeBouteilleDev).isNotEmpty();
        assertThat(listeBouteilleDev.size()).isEqualTo(3);
    }

    @Test
    public void test_BouteilleRepository_findByCouleur() {
        final List<Bouteille> listeBouteilleDev = bouteilleRepository.findByCouleur(Bouteille.Couleur.BLANC);

        assertThat(listeBouteilleDev).isNotNull();
        assertThat(listeBouteilleDev).isNotEmpty();
        assertThat(listeBouteilleDev.size()).isEqualTo(2);
    }

    @Test
    public void test_UtilisateurRepository_findByPseudo() {
        String pseudo = "georgelucas@email.fr";
        final Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getPseudo()).isNotNull();
        assertThat(utilisateur.getPseudo()).isEqualTo(pseudo);
    }

    @Test
    void test_UtilisateurRepository_findByPseudo_inconnu() {
        String pseudo = "george";
        final Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);

        assertThat(utilisateur).isNull();
    }

    @Test
    public void test_UtilisateurRepository_findByPseudoAndPassword() {
        String pseudo = "georgelucas@email.fr";
        String pwd = "Réalisateur&Producteur";
        final Utilisateur utilisateur = utilisateurRepository.findByPseudoAndPassword(pseudo, pwd);

        assertThat(utilisateur).isNotNull();
        assertThat(utilisateur.getPseudo()).isNotNull();
        assertThat(utilisateur.getPseudo()).isEqualTo(pseudo);
        assertThat(utilisateur.getPassword()).isNotNull();
        assertThat(utilisateur.getPassword()).isEqualTo(pwd);
    }

    @Test
    void test_UtilisateurRepository_findByPseudoAndPassword_faux() {
        String pseudo = "georgelucas@email.fr";
        String pwd = "Réalisateur&P";
        final Utilisateur utilisateur = utilisateurRepository.findByPseudoAndPassword(pseudo, pwd);

        assertThat(utilisateur).isNull();
    }
}
