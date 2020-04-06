package by.project.onlinebooking.dto;

import by.project.onlinebooking.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private Role role;
}
