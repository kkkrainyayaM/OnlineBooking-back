package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.PassengerDTO;
import by.project.onlinebooking.entities.Passenger;
import org.junit.Assert;
import org.junit.Test;

public class PassengerMapperTest {

    @Test
    public void givenPassengerToPassengerTdo__whenMaps__thenCorrect() {
        Passenger passenger = new Passenger();
        passenger.setPointDeparture( "departurePoint" );
        passenger.setPointArrival( "arrivalPoint" );
        passenger.setIdRoute( 1 );
        passenger.setIdUser( 1 );

        PassengerDTO passengerDto = PassengerMapper.INSTANCE.passengerToPassengerDto( passenger );

        Assert.assertEquals( passenger.getIdUser(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getPointArrival(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getPointDeparture(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getIdRoute(), passengerDto.getRouteId() );

    }

    @Test
    public void givenPassengerTdoToPassenger__whenMaps__thenCorrect() {
        PassengerDTO passengerDto = new PassengerDTO();
        passengerDto.setDeparturePoint( "departurePoint" );
        passengerDto.setArrivalPoint( "arrivalPoint" );
        passengerDto.setRouteId( 1 );
        passengerDto.setUserId( 1 );

        Passenger passenger = PassengerMapper.INSTANCE.passengerDtoToPassenger( passengerDto );

        Assert.assertEquals( passenger.getIdUser(), passengerDto.getUserId() );
        Assert.assertEquals( passenger.getPointArrival(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getPointDeparture(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( passenger.getIdRoute(), passengerDto.getRouteId() );

    }

}