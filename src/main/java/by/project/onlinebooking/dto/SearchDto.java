package by.project.onlinebooking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class SearchDto {
    private Date date;

    @NotEmpty
    private String departurePoint;

    @NotEmpty
    private String arrivalPoint;

    private int numberOfPlaces = 1;
}
