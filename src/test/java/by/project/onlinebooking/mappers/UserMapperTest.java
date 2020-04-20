package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;


public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper( UserMapper.class );

    @Test
    public void userToTdoMapping() {
        User user = new User();
        user.setId( 1 );
        user.setFirstName( "firstName" );
        user.setLastName( "lastName" );
        user.setPassword( "password" );
        user.setPhone( "phone" );
        user.setRole( Role.USER );

        UserDto UserDto = userMapper.userToUserDto( user );

        Assert.assertEquals( user.getId(), UserDto.getId() );
        Assert.assertEquals( user.getFirstName(), UserDto.getFirstName() );
        Assert.assertEquals( user.getLastName(), UserDto.getLastName() );
        Assert.assertEquals( user.getPhone(), UserDto.getPhone() );
    }

    @Test
    public void tdoToUserMapping() {
        UserDto userDto = new UserDto();
        userDto.setId( 1 );
        userDto.setFirstName( "firstName" );
        userDto.setLastName( "lastName" );
        userDto.setPhone( "phone" );

        User user = userMapper.userDtoToUser( userDto );

        Assert.assertEquals( userDto.getId(), user.getId() );
        Assert.assertEquals( userDto.getFirstName(), user.getFirstName() );
        Assert.assertEquals( userDto.getLastName(), user.getLastName() );
        Assert.assertEquals( userDto.getPhone(), user.getPhone() );
        Assert.assertEquals( Role.USER, user.getRole() );
        Assert.assertNotNull( user.getPassword() );
    }
}
