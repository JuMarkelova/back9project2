package ru.back.app.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.back.app.entity.Role;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="users")
@ToString
@EqualsAndHashCode
public class UserDto {
    @Size(min=2, max=50)
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @Size(max=50)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 50)
    private String password;

    @NotNull(message = "Role must be specified")
    private Role role;
}
