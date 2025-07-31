package fr.eni.caveavin.bll;

import fr.eni.caveavin.bo.Client;
import fr.eni.caveavin.dal.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestClientService {

    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the clientService with the mocked clientRepository
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(clientRepository);
    }

    @Test
    public void testCreateValidClient() {
        // Arrange : create a valid client object
        int id = 1;
        String pseudo = "validUser";
        Client client = Client.builder()
                .id(id)
                .pseudo(pseudo)
                .nom("Doe")
                .prenom("John")
                .password("securePassword")
                .build();

        // Define the behavior of the mocked repository
        when(clientRepository.findByPseudo(pseudo)).thenReturn(Optional.empty());
        when(clientRepository.findById(id)).thenReturn(Optional.of(client));

        // Act : Call the method to test
        clientService.createClient(client);

        // Assert : verify that the client was saved correctly
        Optional<Client> savedClient = clientRepository.findById(id);
        assertTrue(savedClient.isPresent());
        assertThat(savedClient.get().getPseudo()).isEqualTo(pseudo);
        assertThat(savedClient.get().getNom()).isEqualTo(client.getNom());
        assertThat(savedClient.get().getPrenom()).isEqualTo(client.getPrenom());
        assertThat(savedClient.get().getPassword()).isEqualTo(client.getPassword());
    }

    @Test
    public void testCreateClientWithNull() {
        assertThrows(NullPointerException.class, () -> {
            clientService.createClient(null);
        });
    }

    @Test
    public void testCreateClientWithExistingPseudo() {
        // This test will check the createClient method with an existing pseudo.
        String pseudo = "existingUser";
        Client existingClient = Client.builder()
                .id(1)
                .pseudo(pseudo)
                .nom("Doe")
                .prenom("Jane")
                .password("password123")
                .build();

        // Define the behavior of the mocked repository
        when(clientRepository.findByPseudo(pseudo)).thenReturn(Optional.of(existingClient));

        // Call the method to test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            clientService.createClient(existingClient);
        });
    }

    @Test
    public void testCreateClientWithEmptyPseudo() {
        // This test will check the createClient method with a pseudo written with only spaces.
        Client client = Client.builder()
                .id(1)
                .pseudo("  ") // Empty pseudo
                .nom("Doe")
                .prenom("John")
                .password("securePassword")
                .build();

        // Call the method to test and expect an exception
        assertThrows(IllegalArgumentException.class, () -> {
            clientService.createClient(client);
        });
    }

}
