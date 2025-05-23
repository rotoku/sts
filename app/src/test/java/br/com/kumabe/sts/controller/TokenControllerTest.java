import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.kumabe.sts.controller.dto.TokenRequestDto;
import br.com.kumabe.sts.service.TokenService;
import com.example.sts.controller.TokenController;
import com.example.sts.controller.dto.TokenResponseDto;
import com.example.sts.exception.InvalidClientCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TokenController.class)
public class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private TokenController tokenController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken_Success() throws Exception {
        TokenResponseDto responseDto = new TokenResponseDto();
        responseDto.setAccessToken("mockAccessToken");
        responseDto.setTokenType("Bearer");
        responseDto.setExpiresIn(3600);
        responseDto.setRefreshToken("mockRefreshToken");
        responseDto.setScope("read write");
        responseDto.setActive(true);

        when(tokenService.generateToken(any(TokenRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("grant_type", "client_credentials")
                .param("client_id", "mockClientId")
                .param("client_secret", "mockClientSecret"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.access_token").value("mockAccessToken"))
                .andExpect(jsonPath("$.token_type").value("Bearer"))
                .andExpect(jsonPath("$.expires_in").value(3600))
                .andExpect(jsonPath("$.refresh_token").value("mockRefreshToken"))
                .andExpect(jsonPath("$.scope").value("read write"))
                .andExpect(jsonPath("$.active").value(true));

        verify(tokenService, times(1)).generateToken(any(TokenRequestDto.class));
    }

    @Test
    public void testGenerateToken_InvalidCredentials() throws Exception {
        when(tokenService.generateToken(any(TokenRequestDto.class))).thenThrow(new InvalidClientCredentialsException());

        mockMvc.perform(post("/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("grant_type", "client_credentials")
                .param("client_id", "invalidClientId")
                .param("client_secret", "invalidClientSecret"))
                .andExpect(status().isUnauthorized());

        verify(tokenService, times(1)).generateToken(any(TokenRequestDto.class));
    }
}