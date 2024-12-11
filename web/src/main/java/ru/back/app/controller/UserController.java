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
import ru.back.app.jwt.JwtUtil;

@RestController
@RequestMapping("/web")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserFeignClient userFeignClient;
    private final JwtUtil jwtUtil;

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
            log.info("Sending login request to core via Feign with: {}", loginRequestDto);
            UserResponseDto user = userFeignClient.validateUser(loginRequestDto).getBody();

            if (user != null) {
                String token = jwtUtil.generateToken(user.getEmail());
                return ResponseEntity.ok(token);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (Exception e) {
            log.error("Error during login", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    /* public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            log.info("Sending login request to core via Feign with: {}", loginRequestDto);
            return ResponseEntity.ok(String.valueOf(userFeignClient.loginUser(loginRequestDto)));
        } catch (Exception e) {
            log.error("Error during login", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    } */
}