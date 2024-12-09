package ru.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.back.app.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
