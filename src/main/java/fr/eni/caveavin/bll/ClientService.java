package fr.eni.caveavin.bll;

import fr.eni.caveavin.bo.Client;

import java.util.List;

public interface ClientService {
    /**
     * Creates a new client.
     *
     * @param client the client to create
     */
    public void createClient(Client client);

    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public List<Client> getAllClients();

    /**
     * Retrieves a client by its ID.
     *
     * @param id the ID of the client to retrieve
     * @return the client with the specified ID, or null if not found
     */
    public Client getClientById(int id);

    /**
     * Updates an existing client.
     *
     * @param client the client to update
     */
    public void updateClient(Client client);

    /**
     * Deletes a client by its ID.
     *
     * @param id the ID of the client to delete
     */
    public void deleteClient(int id);
}
