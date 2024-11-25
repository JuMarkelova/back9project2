package ru.back.app.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product") // Исправлено имя таблицы
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    // Связь с Brand через @ManyToOne
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false) // Связываем с колонкой brand_id
    private Brand brand;
}