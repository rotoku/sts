package br.com.kumabe.sts.repository;

import br.com.kumabe.sts.domain.Client;
import java.util.Optional;


public interface ClientRepository {
    Optional<Client> findByClientId(String clientId);

    boolean existsByClientIdAndClientSecret(String clientId, String clientSecret);
}