package br.com.kumabe.sts.service;

import br.com.kumabe.sts.controller.dto.TokenRequestDto;
import br.com.kumabe.sts.controller.dto.TokenResponseDto;

public interface TokenService {
    TokenResponseDto generateToken(TokenRequestDto tokenRequest);
}