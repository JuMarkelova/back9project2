package ru.back.app.mapper;

import org.mapstruct.Mapper;
import ru.back.app.dto.UserDto;
import ru.back.app.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserDto userDto);
}
