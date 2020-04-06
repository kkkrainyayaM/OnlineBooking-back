package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.UserDTO;
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

        UserDTO userDTO = UserMapper.INSTANCE.userToUserDto( user );

        Assert.assertEquals( user.getId(), userDTO.getId() );
        Assert.assertEquals( user.getFirstName(), userDTO.getFirstName() );
        Assert.assertEquals( user.getLastName(), userDTO.getLastName() );
        Assert.assertEquals( user.getPassword(), userDTO.getPassword() );
        Assert.assertEquals( user.getPhone(), userDTO.getPhone() );
        Assert.assertEquals( user.getRole(), userDTO.getRole() );

    }

    @Test
    public void givenUserTdoToUser__whenMaps__thenCorrect() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId( 1 );
        userDTO.setFirstName( "firstName" );
        userDTO.setLastName( "lastName" );
        userDTO.setPassword( "password" );
        userDTO.setPhone( "phone" );
        userDTO.setRole( Role.USER );

        User user = UserMapper.INSTANCE.userDtoToUser( userDTO );

        Assert.assertEquals( userDTO.getId(), user.getId() );
        Assert.assertEquals( userDTO.getFirstName(), user.getFirstName() );
        Assert.assertEquals( userDTO.getLastName(), user.getLastName() );
        Assert.assertEquals( userDTO.getPassword(), user.getPassword() );
        Assert.assertEquals( userDTO.getPhone(), user.getPhone() );
        Assert.assertEquals( userDTO.getRole(), user.getRole() );

    }


}