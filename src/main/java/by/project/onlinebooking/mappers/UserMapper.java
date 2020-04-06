package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.UserDTO;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDTO userToUserDto(User user);

    User userDtoToUser(UserDTO userDTO);
}
