package ru.back.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BrandDto {
    private Long id;

    @Size(min = 2, max = 30)
    @NotBlank(message = "Please write the name")
    private String name;

    @Size(min = 5, max = 50)
    @NotBlank(message = "Please write the description")
    private String description;
}
