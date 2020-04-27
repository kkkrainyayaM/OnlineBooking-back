package by.project.onlinebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class RouteDto {
    private long id;
    private Date date;

    @NotEmpty
    private String departureTime;

    @NotEmpty
    private String arrivalTime;

    @NotEmpty
    private String departurePoint;

    @NotEmpty
    private String arrivalPoint;
}
