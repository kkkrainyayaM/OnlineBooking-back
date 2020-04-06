package by.project.onlinebooking.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class RouteDTO {
    private long id;
    private Date date;
    private String departureTime;
    private String arrivalTime;
    private String departurePoint;
    private String arrivalPoint;
}
