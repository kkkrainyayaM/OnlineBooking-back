package by.project.onlinebooking.dto;

import lombok.Data;

@Data
public class PassengerDto {
    private long id;
    private long routeId;
    private long userId;
    private String departurePoint;
    private String arrivalPoint;
}
