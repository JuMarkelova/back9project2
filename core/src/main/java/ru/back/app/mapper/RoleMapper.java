package ru.back.app.mapper;

import org.mapstruct.Mapper;
import ru.back.app.dto.RoleDto;
import ru.back.app.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);

    Role toEntity(RoleDto roleDto);
}
