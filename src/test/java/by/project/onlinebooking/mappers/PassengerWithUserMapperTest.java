package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PassengerWithUserMapperTest {

    private final PassengerMapper passengerMapper = Mappers.getMapper( PassengerMapper.class );

    @Test
    public void passengerWithUserToTdo() {
        Passenger passenger = new Passenger();
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( 1 );
        passenger.setUserId( 1 );

        User user = new User();
        user.setFirstName( "firstName" );
        user.setPhone( "phone" );

        PassengerDto passengerDto = passengerMapper.passengerToPassengerWithUserDto( passenger, user );

        Assert.assertEquals( passenger.getUserId(), passengerDto.getRouteId() );
        Assert.assertEquals( passenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        Assert.assertEquals( passenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        Assert.assertEquals( user.getFirstName(), passengerDto.getFirstName() );
        Assert.assertEquals( user.getPhone(), passengerDto.getPhone() );
    }
}
