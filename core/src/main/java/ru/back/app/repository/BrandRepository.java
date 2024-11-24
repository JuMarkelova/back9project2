package ru.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.back.app.entity.Brand;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findBrandByName(String name);
}
