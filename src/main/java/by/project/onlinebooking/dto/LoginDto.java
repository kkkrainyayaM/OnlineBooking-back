package by.project.onlinebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {
    @NotEmpty
    String phone;

    @NotEmpty
    String password;
}
