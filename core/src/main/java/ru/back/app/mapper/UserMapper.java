package ru.back.app.mapper;

import org.mapstruct.Mapper;
import ru.back.app.dto.UserDto;
import ru.back.app.dto.UserResponseDto;
import ru.back.app.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

    default UserResponseDto toResponseDto(User user) {
        if (user == null || user.getRole() == null) {
            throw new IllegalArgumentException("User or role is null");
        }
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .roleId(user.getRole().getId())
                .build();
    }
}
