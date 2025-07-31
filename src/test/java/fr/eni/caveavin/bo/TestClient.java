package fr.eni.caveavin.bo;

import fr.eni.caveavin.dal.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@DataJpaTest
public class TestClient {
    /**
     * This class tests the Client entity and its repository methods.
     * It uses Spring's DataJpaTest to set up an in-memory database for testing, and TestEntityManager to make sure
     * that a test checks only one of the ClientRepository method at a time.
     */

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void testClientCreation() {
        final Client client = Client.builder()
                .pseudo("jdoe")
                .nom("Doe")
                .prenom("John")
                .password("password123")
                .build();

        final Client savedClient = clientRepository.save(client);
        log.info(savedClient.toString());

        assertThat(savedClient.getId()).isGreaterThan(0);
    }

    @Test
    public void testClientRetrieval() {
        Client client = Client.builder()
                .pseudo("jdoe")
                .nom("Doe")
                .prenom("John")
                .password("password123")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        log.info(client.toString());

        // Retrieve the client by ID
        int id = client.getId();
        final Optional<Client> retrievedClient = clientRepository.findById(id);

        // Assert that the client is present
        assertThat(retrievedClient.isPresent()).isTrue();

        // Assert that the retrieved client matches the saved client
        final Client foundClient = retrievedClient.get();
        log.info(foundClient.toString());

        // Client validation
        assertThat(foundClient.getId()).isEqualTo(id);
        assertThat(foundClient).isEqualTo(client);
    }

    @Test
    public void testClientUpdate() {
        Client client = Client.builder()
                .pseudo("jdoe")
                .nom("Doe")
                .prenom("John")
                .password("password123")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        assertThat(client.getId()).isGreaterThan(0);
        log.info("ORIGINE : " + client);

        // Update the client's pseudo
        client.setPseudo("johndoe");
        final Client updatedClient = clientRepository.save(client);

        // Assert that the pseudo has been updated
        assertThat(updatedClient.getId()).isEqualTo(client.getId());
        assertThat(updatedClient.getPseudo()).isEqualTo(client.getPseudo());
        log.info("MISE A JOUR / " + updatedClient);
    }

    @Test
    public void testClientDeletion() {
        Client client = Client.builder()
                .pseudo("jdoe")
                .nom("Doe")
                .prenom("John")
                .password("password123")
                .build();

        entityManager.persist(client);
        entityManager.flush();
        assertThat(client.getId()).isGreaterThan(0);
        log.info("ORIGINE : " + client);

        // Delete the client
        clientRepository.delete(client);

        // Assert that the client no longer exists
        final Client deletedClient = entityManager.find(Client.class, client.getId());
        assertNull(deletedClient);
    }

    @Test
    public void testFindAllClients() {
        Client client1 = Client.builder()
                .pseudo("jdoe")
                .nom("Doe")
                .prenom("John")
                .password("password123")
                .build();

        Client client2 = Client.builder()
                .pseudo("asmith")
                .nom("Smith")
                .prenom("Alice")
                .password("password456")
                .build();

        entityManager.persist(client1);
        entityManager.persist(client2);
        entityManager.flush();

        // Retrieve all clients
        List<Client> clients = clientRepository.findAll();
        log.info("Clients found: " + clients);

        // Assert that both clients are present
        assertThat(clients).hasSize(2);
        assertThat(clients).contains(client1, client2);
    }

    @Test
    public void testFindAllWhenEmpty() {
        final List<Client> clients = clientRepository.findAll();
        assertThat(clients).isEmpty();
    }

}
