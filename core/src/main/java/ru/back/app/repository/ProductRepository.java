package ru.back.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.back.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
