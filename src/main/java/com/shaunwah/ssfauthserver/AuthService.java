package com.shaunwah.ssfauthserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthRepository authRepository;

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return authRepository.findByUsernameAndPassword(username, password);
    }
}
