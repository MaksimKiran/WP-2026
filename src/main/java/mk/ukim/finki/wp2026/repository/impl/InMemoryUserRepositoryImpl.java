package mk.ukim.finki.wp2026.repository.impl;

import mk.ukim.finki.wp2026.bootstrap.DataHolder;
import mk.ukim.finki.wp2026.model.User;
import mk.ukim.finki.wp2026.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUsername(String username) {
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return DataHolder.users.stream()
                .filter(u -> u.getUsername().equals(username))
                .filter(u -> u.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public User save(User user) {
        DataHolder.users.removeIf(u -> u.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    @Override
    public void deleteByUsername(String username) {
        DataHolder.users.removeIf(u -> u.getUsername().equals(username));
    }
}
