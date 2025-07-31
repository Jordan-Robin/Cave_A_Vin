package fr.eni.caveavin.bll;

import fr.eni.caveavin.bo.Client;
import fr.eni.caveavin.dal.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    /**
     * This class implements the ClientService interface and provides methods to manage clients.
     * It uses a ClientRepository to perform CRUD operations on the client data.
     */

    private final ClientRepository clientRepository;

    @Override
    public void createClient(Client client) {
        // Validate that the client is not null and has a valid pseudo
        Objects.requireNonNull(client, "Client cannot be null");
        validatePseudo(client.getPseudo());

        // Persist the client to the repository
        clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client with ID " + id + " not found"));
    }

    @Override
    public void updateClient(Client client) {
        // Validate that the client is not null and has a valid ID
        if (client == null || client.getId() <= 0) {
            throw new IllegalArgumentException("Client cannot be null and must have a valid ID");
        }
        // TODO valider le pseudo si changement
        clientRepository.save(client);
    }

    @Override
    public void deleteClient(int id) {
        // Validate that the ID is greater than zero and that the client exists
        if (id <= 0) {
            throw new IllegalArgumentException("Client ID must be greater than zero");
        }
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client with ID " + id + " does not exist");
        }

        // Delete the client by ID
        clientRepository.deleteById(id);
    }

    private void validatePseudo(String pseudo) {
        // Validate that pseudo is not null or empty
        if (pseudo == null || pseudo.isBlank()) {
            throw new IllegalArgumentException("Pseudo cannot be null or empty");
        }

        // Check if the pseudo already exists in the repository
        clientRepository.findByPseudo(pseudo)
                        .ifPresent(c -> {
                            throw new IllegalArgumentException(String.format("Pseudo %s already exists", pseudo));
                        });
    }

}
