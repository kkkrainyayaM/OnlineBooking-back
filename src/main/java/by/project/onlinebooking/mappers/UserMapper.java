package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.RandomPasswordGenerator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    @Mapping(target = "password", source = "userDto", qualifiedByName = "generatePassword")
    @Mapping(target = "role", constant = "USER")
    User userDtoToUser(UserDto userDto);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "userDto.firstName", target = "firstName")
    @Mapping(source = "userDto.lastName", target = "lastName")
    @Mapping(source = "userDto.phone", target = "phone")
    User update(User user, UserDto userDto);

    @Named("generatePassword")
    default String generatePassword(UserDto userDto) {
        return RandomPasswordGenerator.generateRandomPassword();
    }
}
