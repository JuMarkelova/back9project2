package ru.back.app.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WebProductDto {
    private Long id;

    @Size(min = 2, max = 30)
    @NotBlank(message = "Please write the name")
    private String name;

    @NotNull(message = "Please provide the price")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private Double price;

    private String brandName;
}
