package ru.back.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name="users")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    @NotBlank(message = "Name must not be empty")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "Surname must not be empty")
    @Size(max = 50)
    private String surname;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 4)
    private String password;

    @NotNull
    @JsonProperty("roleId")
    private Long roleId;
}
