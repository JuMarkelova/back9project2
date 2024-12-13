package ru.back.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.back.app.client.UserFeignClient;
import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;

@RestController
@RequestMapping("/web")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserFeignClient userFeignClient;

    @PostMapping("/users/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            ResponseEntity<UserResponseDto> response = userFeignClient.registerUser(userDto);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto userDto
    ) {
        try {
            UserResponseDto updatedUser = userFeignClient.updateUser(id, userDto).getBody();
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            log.info("Attempting to log in user via Feign with: {}", loginRequestDto);
            ResponseEntity<String> response = userFeignClient.loginUser(loginRequestDto);

            if (response.getStatusCode() == HttpStatus.OK) {
                log.info("Login successful, token received: {}", response.getBody());
                return ResponseEntity.ok(response.getBody());
            }

            log.warn("Login failed, core service returned: {}", response.getStatusCode());
            return ResponseEntity.status(response.getStatusCode()).body("Login failed");
        } catch (Exception e) {
            log.error("Error during login request to core", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}