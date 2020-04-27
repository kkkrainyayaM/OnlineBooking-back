package by.project.onlinebooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PassengerDto {
    private long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private long routeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotNull
    private long userId;

    @NotEmpty
    private String departurePoint;

    @NotEmpty
    private String arrivalPoint;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Date date;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NotEmpty
    private String departureTime;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NotEmpty
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NotEmpty
    private String phone;
}
