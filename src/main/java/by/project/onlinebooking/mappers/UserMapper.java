package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDTO);
}
