package ru.back.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDto {

    private Long id;
    @Size(min = 2, max = 30)
    @NotBlank(message = "Please write the name")
    private String name;
    @NotBlank(message = "Please write the price")
    private Double price;
    private Long brandId;
}
