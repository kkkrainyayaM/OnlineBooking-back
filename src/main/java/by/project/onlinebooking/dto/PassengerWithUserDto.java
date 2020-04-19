package by.project.onlinebooking.dto;

import lombok.Data;

@Data
public class PassengerWithUserDto {
    private long id;
    private long routeId;
    private String departurePoint;
    private String arrivalPoint;
    private String firstName;
    private String phone;
}
