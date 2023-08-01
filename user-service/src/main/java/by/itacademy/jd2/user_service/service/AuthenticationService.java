package by.itacademy.jd2.user_service.service;

import by.itacademy.jd2.user_service.core.dto.UserLoginDTO;
import by.itacademy.jd2.user_service.core.dto.UserRegistrationDTO;
import by.itacademy.jd2.user_service.core.enums.EUserRole;
import by.itacademy.jd2.user_service.core.enums.EUserStatus;
import by.itacademy.jd2.user_service.dao.api.IUserRepository;
import by.itacademy.jd2.user_service.dao.entity.User;
import by.itacademy.jd2.user_service.core.dto.AuthenticationResponseDTO;
import by.itacademy.jd2.user_service.service.api.IAuthenticationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(UserRegistrationDTO request) {
        var user = User.builder()
                .fio(request.getFio())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(EUserRole.ROLE_USER)
                .status(EUserStatus.WAITING_ACTIVATION)
                .build();

        if (userRepository.findByEmail(request.getEmail()).isEmpty()) {
            userRepository.save(user);
        } else {
            throw new IllegalStateException("Such user exists!"); //TODO handle the Exception;
        }

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO login(UserLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No such email!")); //TODO handle the Exception;

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponseDTO.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public String verification(String mail) {
        Optional<User> userOptional = userRepository.findByEmail(mail);
        User entity = userOptional.orElseThrow(() -> new EntityNotFoundException("No such Entity!")); //TODO handle the Exception;

        entity.setStatus(EUserStatus.ACTIVATED);

        userRepository.save(entity);

        return "confirmed";
    }
}
