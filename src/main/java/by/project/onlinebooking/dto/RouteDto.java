package by.project.onlinebooking.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RouteDto {
    private long id;
    private Date date;
    private String departureTime;
    private String arrivalTime;
    private String departurePoint;
    private String arrivalPoint;
}
