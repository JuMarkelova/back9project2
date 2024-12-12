package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;
import ru.back.app.entity.Role;
import ru.back.app.entity.User;
import ru.back.app.mapper.RoleMapper;
import ru.back.app.mapper.UserMapper;
import ru.back.app.repository.RoleRepository;
import ru.back.app.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserDto userDto) {
        if (userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findById(userDto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.setRole(role);

        User savedUser = userRepository.save(user);
        log.info("Saved user: {}", savedUser);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        if (!optionalUser.get().getEmail().equals(userDto.getEmail())) {
            userRepository.findUserByEmail(userDto.getEmail())
                    .ifPresent(existingUser -> {
                        throw new IllegalArgumentException("Email is already taken by another user");
                    });
        }
        User user = optionalUser.get();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User updatedUser = userRepository.save(user);

        return new UserResponseDto(user.getId(), user.getName(), user.getSurname(), user.getEmail(), user.getRole().getId());
    }

    @Override
    public String loginUser(LoginRequestDto loginRequestDto) {
        log.info("Received login request with email: {}, password: {}", loginRequestDto.getEmail(), loginRequestDto.getPassword());

        User user = userRepository.findUserByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        log.info("User found: {}", user);
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        log.info("Password matches, returning token");
        return "mockedToken"; //реальный токен должен возвращаться тут
    }
}
