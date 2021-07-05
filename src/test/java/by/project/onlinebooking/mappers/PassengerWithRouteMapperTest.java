package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.helpers.PassengerGenerator;
import by.project.onlinebooking.helpers.RouteGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PassengerWithRouteMapperTest {

    private final PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);
    private static final long ID = 1L;

    @Test
    public void passengerToDtoMapping() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );
        Route route = RouteGenerator.generate();

        PassengerDto passengerDto = passengerMapper.passengerToPassengerWithRouteDto(passenger, route);

        Assert.assertEquals(passenger.getUserId(), passengerDto.getUserId());
        Assert.assertEquals(passenger.getArrivalPoint(), passengerDto.getArrivalPoint());
        Assert.assertEquals(passenger.getDeparturePoint(), passengerDto.getDeparturePoint());
        Assert.assertEquals(route.getDate(), passengerDto.getDate());
        Assert.assertEquals(route.getDepartureTime(), passengerDto.getDepartureTime());
    }
}
