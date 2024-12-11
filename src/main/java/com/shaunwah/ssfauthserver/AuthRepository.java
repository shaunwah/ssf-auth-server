package com.shaunwah.ssfauthserver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthRepository {
    // hardcoded values -- not recommended!!!
    private final List<User> users = new ArrayList<>();

    public AuthRepository() {
        users.add(new User("fred", "fredfred"));
        users.add(new User("barney", "barneybarney"));
        users.add(new User("wilma", "wilmawilma"));
        users.add(new User("betty", "bettybetty"));
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
}
