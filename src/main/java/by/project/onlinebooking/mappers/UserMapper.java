package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mapping(target = "role", constant = "USER")
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "userDto.firstName", target = "firstName")
    @Mapping(source = "userDto.lastName", target = "lastName")
    @Mapping(source = "userDto.phone", target = "phone")
    User update(User user, UserDto userDto);
}
