package by.project.onlinebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class RouteDto {
    private long id;
    private LocalDate date;

    @NotEmpty
    private String departureTime;

    @NotEmpty
    private String arrivalTime;

    @NotEmpty
    private String departurePoint;

    @NotEmpty
    private String arrivalPoint;
}
