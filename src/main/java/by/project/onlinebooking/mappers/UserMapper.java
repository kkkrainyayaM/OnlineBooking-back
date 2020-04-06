package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.UserDTO;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);
}
