package com.shaunwah.ssfauthserver;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AuthController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<Message> login(@RequestBody User user) {
        try {
            if (user == null || user.getUsername() == null|| user.getPassword() == null) {
                throw new Exception("Invalid payload");
            }
            return authService.findByUsernameAndPassword(user.getUsername(), user.getPassword())
                    .map(u -> ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message("Authenticated %s".formatted(u.getUsername())))
                    )
                    .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Message("Incorrect username and/or password")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Message("Invalid payload"));
        }

    }
}
