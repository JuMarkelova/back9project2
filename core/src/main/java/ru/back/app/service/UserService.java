package ru.back.app.service;

import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;

public interface UserService {

    UserDto registerUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    String loginUser(LoginRequestDto loginRequestDto);
}
