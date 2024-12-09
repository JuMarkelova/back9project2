package ru.back.app.service;

import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;

public interface UserService {

    UserResponseDto registerUser(UserDto userDto);
    UserResponseDto updateUser(Long id, UserDto userDto);
    String loginUser(LoginRequestDto loginRequestDto);
}
