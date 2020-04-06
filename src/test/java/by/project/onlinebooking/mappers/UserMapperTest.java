package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.User;
import org.junit.Assert;
import org.junit.Test;

public class UserMapperTest {

    @Test
    public void givenUserToUserTdo__whenMaps__thenCorrect() {
        User user = new User();
        user.setId( 1 );
        user.setFirstName( "firstName" );
        user.setLastName( "lastName" );
        user.setPassword( "password" );
        user.setPhone( "phone" );
        user.setRole( Role.USER );

        UserDto UserDto = UserMapper.INSTANCE.userToUserDto( user );

        Assert.assertEquals( user.getId(), UserDto.getId() );
        Assert.assertEquals( user.getFirstName(), UserDto.getFirstName() );
        Assert.assertEquals( user.getLastName(), UserDto.getLastName() );
        Assert.assertEquals( user.getPassword(), UserDto.getPassword() );
        Assert.assertEquals( user.getPhone(), UserDto.getPhone() );
        Assert.assertEquals( user.getRole(), UserDto.getRole() );

    }

    @Test
    public void givenUserTdoToUser__whenMaps__thenCorrect() {
        UserDto userDto = new UserDto();
        userDto.setId( 1 );
        userDto.setFirstName( "firstName" );
        userDto.setLastName( "lastName" );
        userDto.setPassword( "password" );
        userDto.setPhone( "phone" );
        userDto.setRole( Role.USER );

        User user = UserMapper.INSTANCE.userDtoToUser( userDto );

        Assert.assertEquals( userDto.getId(), user.getId() );
        Assert.assertEquals( userDto.getFirstName(), user.getFirstName() );
        Assert.assertEquals( userDto.getLastName(), user.getLastName() );
        Assert.assertEquals( userDto.getPassword(), user.getPassword() );
        Assert.assertEquals( userDto.getPhone(), user.getPhone() );
        Assert.assertEquals( userDto.getRole(), user.getRole() );

    }


}