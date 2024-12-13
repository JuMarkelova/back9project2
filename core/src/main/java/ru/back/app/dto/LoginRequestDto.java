package ru.back.app.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
public class LoginRequestDto {
    private String email;
    private String password;
}
