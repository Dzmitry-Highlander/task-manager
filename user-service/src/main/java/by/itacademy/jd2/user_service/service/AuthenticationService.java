package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.endpoitns.web.auth.AuthenticationRequest;
import by.itacademy.jd2.user_service.endpoitns.web.auth.AuthenticationResponse;
import by.itacademy.jd2.user_service.endpoitns.web.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(EUserRole.ROLE_USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); //TODO handle the Exception;

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
