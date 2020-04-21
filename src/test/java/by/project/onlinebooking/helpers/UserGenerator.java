package by.project.onlinebooking.helpers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.User;

public class UserGenerator {

    public static User generate() {
        User user = new User();
        user.setId( 1 );
        user.setFirstName( "firstName" );
        user.setLastName( "lastName" );
        user.setPassword( "password" );
        user.setRole( Role.USER );
        user.setPhone( "phone" );
        return user;
    }

    public static UserDto generateDto() {
        UserDto user = new UserDto();
        user.setId( 1 );
        user.setFirstName( "firstName" );
        user.setLastName( "lastName" );
        user.setPhone( "phone" );
        return user;
    }
}
