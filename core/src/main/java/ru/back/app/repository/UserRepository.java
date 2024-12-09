package ru.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.back.app.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
