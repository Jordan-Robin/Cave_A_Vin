package fr.eni.caveavin.bo;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;

import fr.eni.caveavin.dal.ClientRepository;
import fr.eni.caveavin.dal.ProprioRepository;
import fr.eni.caveavin.dal.UtilisateurRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DataJpaTest
public class TestHeritage {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Autowired
    ProprioRepository proprioRepository;

    @Autowired
    ClientRepository clientRepository;

    @Test
    public void test_findAll_Utilisateur() {
        final List<Utilisateur> utilisateurs = utilisateurRepository.findAll();

        assertThat(utilisateurs).isNotNull();
        assertThat(utilisateurs).isNotEmpty();
        assertThat(utilisateurs.size()).isEqualTo(3);
        log.info(utilisateurs.toString());
    }

    @Test
    public void test_findAll_Proprio() {
        final List<Proprio> proprios = proprioRepository.findAll();

        assertThat(proprios).isNotNull();
        assertThat(proprios).isNotEmpty();
        assertThat(proprios.size()).isEqualTo(1);
        log.info(proprios.toString());
    }

    @Test
    public void test_findAll_Client() {
        final List<Client> clients = clientRepository.findAll();

        assertThat(clients).isNotNull();
        assertThat(clients).isNotEmpty();
        assertThat(clients.size()).isEqualTo(1);
        log.info(clients.toString());
    }

}