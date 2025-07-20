package com.example.alura.forum.services;

import com.example.alura.forum.dtos.requests.AuthRequestDto;
import com.example.alura.forum.dtos.responses.AuthResponseDto;
import com.example.alura.forum.entities.Usuario;
import com.example.alura.forum.repositories.UsuarioRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponseDto autenticar(AuthRequestDto authDto) {
        var usuarioOptional = usuarioRepository.findByLogin(authDto.login());

        if(usuarioOptional.isEmpty()){
            throw new BadCredentialsException("Email ou senha incorretos");
        }

        var usuario = usuarioOptional.get();

        var isValidLogin = bCryptPasswordEncoder.matches(authDto.password(), usuario.getPassword());

        if (Boolean.FALSE.equals(isValidLogin)){
            throw new BadCredentialsException("Email ou senha incorretos");
        }

        return jwtService.generateToken(usuario);
    }
}
