package ru.back.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.back.app.dto.LoginRequestDto;
import ru.back.app.dto.UserDto;
import ru.back.app.entity.Role;
import ru.back.app.entity.User;
import ru.back.app.mapper.RoleMapper;
import ru.back.app.mapper.UserMapper;
import ru.back.app.repository.RoleRepository;
import ru.back.app.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        User user = userMapper.toEntity(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName(userDto.getRoleDto().getName())
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.setRole(role);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public String loginUser(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        return "mockedToken";
    }
}
