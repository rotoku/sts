package br.com.kumabe.sts.infrastructure.persistence;

import br.com.kumabe.sts.domain.Client;
import br.com.kumabe.sts.repository.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class InMemoryClientRepository implements ClientRepository {
    private final Map<String, Client> clients = new HashMap<>();

    // Sample client for demonstration purposes
    @Override
    public Optional<Client> findByClientId(String clientId) {
        Optional<Client> clientOptional = Optional.ofNullable(clients.get(clientId));
        if (clientOptional.isPresent()) {
            return clientOptional;
        } else {
            // If the client is not found, return an empty Optional
            return Optional.empty();
        }
    }

    @Override
    public boolean existsByClientIdAndClientSecret(String clientId, String clientSecret) {
        // Check if the client exists and if the secret matches
        Optional<Client> clientOptional = findByClientId(clientId);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return client.getClientSecret().equals(clientSecret);
        }
        return false;
    }

    public InMemoryClientRepository() {
        // Sample client for demonstration purposes
        clients.put("client_id_1", new Client("client_id_1", "client_secret_1"));
    }

}