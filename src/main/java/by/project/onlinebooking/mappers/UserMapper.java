package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.UserDTO;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
