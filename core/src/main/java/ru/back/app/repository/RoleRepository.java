package ru.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.back.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
