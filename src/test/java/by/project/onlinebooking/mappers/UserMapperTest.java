package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.UserGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;


public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void userToTdoMapping() {
        User user = UserGenerator.generate();

        UserDto userDto = userMapper.userToUserDto(user);

        Assert.assertEquals(user.getId(), userDto.getId());
        Assert.assertEquals(user.getFirstName(), userDto.getFirstName());
        Assert.assertEquals(user.getLastName(), userDto.getLastName());
        Assert.assertEquals(user.getPhone(), userDto.getPhone());
    }

    @Test
    public void tdoToUserMapping() {
        UserDto userDto = UserGenerator.generateDto();

        User user = userMapper.userDtoToUser(userDto);

        Assert.assertEquals(userDto.getId(), user.getId());
        Assert.assertEquals(userDto.getFirstName(), user.getFirstName());
        Assert.assertEquals(userDto.getLastName(), user.getLastName());
        Assert.assertEquals(userDto.getPhone(), user.getPhone());
    }
}
