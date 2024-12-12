package ru.back.app.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;

@FeignClient(name = "core", url = "http://localhost:8080")
public interface UserFeignClient {

    @PostMapping("users/register")
    ResponseEntity<UserResponseDto> registerUser(@RequestBody UserDto userDto);

    @PutMapping("users/{id}")
    ResponseEntity<UserResponseDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto);

    @PostMapping("/auth/login")
    ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto);

    @PostMapping("/auth/validate")
    ResponseEntity<UserResponseDto> validateUser(@RequestBody LoginRequestDto loginRequestDto);
}