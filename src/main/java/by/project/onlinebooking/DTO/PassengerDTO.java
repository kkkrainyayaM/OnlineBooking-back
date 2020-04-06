package by.project.onlinebooking.DTO;

import lombok.Data;

@Data
public class PassengerDTO {
    private long routeId;
    private long userId;
    private String departurePoint;
    private String arrivalPoint;
}
