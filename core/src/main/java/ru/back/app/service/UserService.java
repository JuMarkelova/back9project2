package ru.back.app.service;

import org.springframework.http.ResponseEntity;
import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserDto userDto);
    UserResponseDto updateUser(Long id, UserDto userDto);
    ResponseEntity<String> loginUser(LoginRequestDto loginRequestDto);
}
