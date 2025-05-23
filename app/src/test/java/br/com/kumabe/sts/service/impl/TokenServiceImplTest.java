package br.com.kumabe.sts.service.impl;

import br.com.kumabe.sts.exception.InvalidClientCredentialsException;
import com.example.sts.domain.AccessToken;
import com.example.sts.domain.Client;
import com.example.sts.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateToken_Success() {
        // Arrange
        String grantType = "client_credentials";
        String clientId = "test-client";
        String clientSecret = "test-secret";

        when(clientRepository.findByClientId(clientId)).thenReturn(new Client(clientId, clientSecret));

        // Act
        AccessToken token = tokenService.generateToken(grantType, clientId, clientSecret);

        // Assert
        assertNotNull(token);
        assertEquals("Bearer", token.getTokenType());
        assertTrue(token.getExpiresIn() > 0);
        assertNotNull(token.getAccessToken());
        assertNotNull(token.getRefreshToken());
        assertTrue(token.isActive());
    }

    @Test
    void testGenerateToken_InvalidClient() {
        // Arrange
        String grantType = "client_credentials";
        String clientId = "invalid-client";
        String clientSecret = "invalid-secret";

        when(clientRepository.findByClientId(clientId)).thenReturn(null);

        // Act & Assert
        assertThrows(InvalidClientCredentialsException.class, () -> {
            tokenService.generateToken(grantType, clientId, clientSecret);
        });
    }
}