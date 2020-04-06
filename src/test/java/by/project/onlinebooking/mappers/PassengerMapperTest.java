package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import org.junit.Assert;
import org.junit.Test;

public class PassengerMapperTest {

    @Test
    public void givenPassengerToPassengerTdo__whenMaps__thenCorrect() {
        Passenger passenger = new Passenger();
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setIdRoute( 1 );
        passenger.setIdUser( 1 );

        PassengerDto passengerDto = PassengerMapper.INSTANCE.passengerToPassengerDto( passenger );

        Assert.assertEquals( passenger.getIdUser(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getIdRoute(), passengerDto.getRouteId() );

    }

    @Test
    public void givenPassengerTdoToPassenger__whenMaps__thenCorrect() {
        PassengerDto passengerDto = new PassengerDto();
        passengerDto.setDeparturePoint( "departurePoint" );
        passengerDto.setArrivalPoint( "arrivalPoint" );
        passengerDto.setRouteId( 1 );
        passengerDto.setUserId( 1 );

        Passenger passenger = PassengerMapper.INSTANCE.passengerDtoToPassenger( passengerDto );

        Assert.assertEquals( passenger.getIdUser(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getIdRoute(), passengerDto.getRouteId() );

    }

}