package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;


public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void userToDtoMapping() {
        User user = new User();
        user.setId(1);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setPhone("phone");
        user.setRole(Role.USER);

        UserDto userDto = userMapper.userToUserDto(user);

        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
        Assert.assertEquals(user.getPhone(), userDto.getPhone());
    }

    @Test
    public void dtoToUserMapping() {
        UserDto userDto = new UserDto();
        userDto.setId(1);
        userDto.setFirstName("firstName");
        userDto.setLastName("lastName");
        userDto.setPhone("phone");

        User user = userMapper.userDtoToUser(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
        Assert.assertEquals(userDto.getPhone(), user.getPhone());
        Assert.assertEquals(Role.USER, user.getRole());
        Assert.assertNotNull(user.getPassword());
    }
}
