package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.helpers.PassengerGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PassengerMapperTest {

    private final PassengerMapper passengerMapper = Mappers.getMapper( PassengerMapper.class );

    @Test
    public void passengerToTdoMapping() {
        Passenger passenger = PassengerGenerator.generate();

        PassengerDto passengerDto = passengerMapper.passengerToPassengerDto( passenger );

        Assert.assertEquals( passenger.getUserId(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getRouteId(), passengerDto.getRouteId() );
    }

    @Test
    public void tdoToPassengerMapping() {
        PassengerDto passengerDto = PassengerGenerator.generateDto();

        Passenger passenger = passengerMapper.passengerDtoToPassenger( passengerDto );

        Assert.assertEquals( passenger.getUserId(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getRouteId(), passengerDto.getRouteId() );
    }
}
