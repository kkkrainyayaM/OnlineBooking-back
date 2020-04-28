package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.PassengerGenerator;
import by.project.onlinebooking.helpers.UserGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class PassengerWithUserMapperTest {

    private static final long ID = 1L;
    private final PassengerMapper passengerMapper = Mappers.getMapper(PassengerMapper.class);

    @Test
    public void passengerWithUserToDto() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );

        User user = UserGenerator.generate();

        PassengerDto passengerDto = passengerMapper.passengerToPassengerWithUserDto(passenger, user);

        Assert.assertEquals(passenger.getUserId(), passengerDto.getRouteId());
        Assert.assertEquals(passenger.getArrivalPoint(), passengerDto.getArrivalPoint());
        Assert.assertEquals(passenger.getDeparturePoint(), passengerDto.getDeparturePoint());
        Assert.assertEquals(user.getFirstName(), passengerDto.getFirstName());
        Assert.assertEquals(user.getPhone(), passengerDto.getPhone());
    }
}
