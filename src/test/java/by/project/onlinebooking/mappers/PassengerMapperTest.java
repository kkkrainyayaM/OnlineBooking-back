package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.helpers.PassengerGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PassengerMapperTest {

    private static final long ID = 1L;
    private final PassengerMapper passengerMapper = Mappers.getMapper( PassengerMapper.class );

    @Test
    public void passengerToDtoMapping() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );

        PassengerDto passengerDto = passengerMapper.passengerToPassengerDto(passenger);

        Assert.assertEquals(passenger.getUserId(), passengerDto.getUserId());
        Assert.assertEquals(passenger.getArrivalPoint(), passengerDto.getArrivalPoint());
        Assert.assertEquals(passenger.getDeparturePoint(), passengerDto.getDeparturePoint());
        Assert.assertEquals(passenger.getRouteId(), passengerDto.getRouteId());
    }

    @Test
    public void DtoToPassengerMapping() {
        PassengerDto passengerDto = PassengerGenerator.generateDto( ID, ID );

        Passenger passenger = passengerMapper.passengerDtoToPassenger(passengerDto);

        Assert.assertEquals(passenger.getUserId(), passengerDto.getUserId());
        Assert.assertEquals(passenger.getArrivalPoint(), passengerDto.getArrivalPoint());
        Assert.assertEquals(passenger.getDeparturePoint(), passengerDto.getDeparturePoint());
        Assert.assertEquals(passenger.getRouteId(), passengerDto.getRouteId());
    }
}
