package by.project.onlinebooking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerWithRouteDto {
    private long userId;
    private String departurePoint;
    private String arrivalPoint;
    private Date date;
    private String departureTime;

}