package by.project.onlinebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class SearchDto {
    private LocalDate date;

    @NotEmpty
    private String departurePoint;

    @NotEmpty
    private String arrivalPoint;

    private int numberOfPlaces = 1;
}
