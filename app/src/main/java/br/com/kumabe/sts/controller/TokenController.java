package br.com.kumabe.sts.controller;

import br.com.kumabe.sts.controller.dto.TokenRequestDto;
import br.com.kumabe.sts.controller.dto.TokenResponseDto;
import br.com.kumabe.sts.exception.InvalidClientCredentialsException;
import br.com.kumabe.sts.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apí/oauth/token")
public class TokenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);
    private final TokenService tokenService;

    @Autowired
    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<TokenResponseDto> generateToken(TokenRequestDto tokenRequest) {
        try {
            TokenResponseDto tokenResponse = tokenService.generateToken(tokenRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
        } catch (InvalidClientCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}