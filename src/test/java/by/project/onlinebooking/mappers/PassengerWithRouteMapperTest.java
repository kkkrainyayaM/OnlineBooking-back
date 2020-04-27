package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Route;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

public class PassengerWithRouteMapperTest {

    private final PassengerMapper passengerMapper = Mappers.getMapper( PassengerMapper.class );

    @Test
    public void passengerToTdoMapping() {
        Passenger passenger = new Passenger();
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( 1 );
        passenger.setUserId( 1 );

        Route route = new Route();
        route.setDate( new Date() );
        route.setDepartureTime( "11:00" );

        PassengerDto passengerDto = passengerMapper.passengerToPassengerWithRouteDto( passenger, route );

        Assert.assertEquals( passenger.getUserId(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( route.getDate(), passengerDto.getDate() );
        Assert.assertEquals( route.getDepartureTime(), passengerDto.getDepartureTime() );
    }
}
