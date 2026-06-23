package dev.trajano.restaurante.service;

import dev.trajano.restaurante.domain.entity.User;
import dev.trajano.restaurante.dto.AuthResponse;
import dev.trajano.restaurante.dto.LoginRequest;
import dev.trajano.restaurante.dto.RegisterRequest;
import dev.trajano.restaurante.exceptions.BusinessException;
import dev.trajano.restaurante.exceptions.NotFoundException;
import dev.trajano.restaurante.repository.UserRepository;
import dev.trajano.restaurante.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent())
            throw new BusinessException("E-mail já cadastrado");

        User user = new User();
        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getNome(), user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getNome(), user.getRole().name());
    }
}
