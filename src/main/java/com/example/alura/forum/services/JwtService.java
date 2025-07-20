package com.example.alura.forum.services;

import com.example.alura.forum.dtos.responses.AuthResponseDto;
import com.example.alura.forum.entities.Usuario;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {

    private final JwtEncoder jwtEncoder;

    public JwtService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public AuthResponseDto generateToken(Usuario usuario){
        Instant now = Instant.now();
        long tokenExpiresIn = 360L;
        long refreshTokenExpiresIn = 3600L;

        var claimsToken = JwtClaimsSet.builder()
                .issuer("forum-alura")
                .subject(usuario.getLogin())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(tokenExpiresIn))
                .build();

        var claimsRefreshToken = JwtClaimsSet.builder()
                .issuer("forum-alura")
                .subject(usuario.getLogin())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(refreshTokenExpiresIn))
                .build();

        var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claimsToken)).getTokenValue();
        var jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(claimsRefreshToken)).getTokenValue();

        return new AuthResponseDto(jwtToken, tokenExpiresIn, jwtRefreshToken);
    }
}
