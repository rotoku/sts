package br.com.kumabe.sts.service.impl;

import br.com.kumabe.sts.controller.dto.TokenRequestDto;
import br.com.kumabe.sts.controller.dto.TokenResponseDto;
import br.com.kumabe.sts.domain.AccessToken;
import br.com.kumabe.sts.exception.InvalidClientCredentialsException;
import br.com.kumabe.sts.repository.ClientRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class TokenServiceImpl implements TokenService {

    private final ClientRepository clientRepository;

    @Autowired
    public TokenServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public TokenResponseDto generateToken(TokenRequestDto tokenRequest) {
        validateClient(tokenRequest.getClient_id(), tokenRequest.getClient_secret());

        AccessToken accessToken = createAccessToken();
        return new TokenResponseDto(
                accessToken.getAccessToken(),
                "Bearer",
                accessToken.getExpiresIn(),
                accessToken.getRefreshToken(),
                accessToken.getScope(),
                true
        );
    }

    private void validateClient(String clientId, String clientSecret) {
        if (!clientRepository.existsByClientIdAndClientSecret(clientId, clientSecret)) {
            throw new InvalidClientCredentialsException("Invalid client credentials");
        }
    }

    private AccessToken createAccessToken() {
        String token = UUID.randomUUID().toString();
        long expiresIn = 3600; // 1 hour
        String refreshToken = UUID.randomUUID().toString();
        String scope = "read write";
        String tokenType = "Bearer";
        boolean active = true;
        return new AccessToken(token, tokenType, expiresIn, refreshToken, scope, active);
    }
}