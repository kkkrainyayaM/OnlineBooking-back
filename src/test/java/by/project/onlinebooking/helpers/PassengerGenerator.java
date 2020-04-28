package by.project.onlinebooking.helpers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;

public class PassengerGenerator {

    public static Passenger generate(long userId, long routeId) {
        Passenger passenger = new Passenger();
        passenger.setId( 1 );
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( routeId );
        passenger.setUserId( userId );
        return passenger;
    }

    public static PassengerDto generateDto(long userId, long routeId) {
        PassengerDto passenger = new PassengerDto();
        passenger.setId( 1 );
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( routeId );
        passenger.setUserId( userId );
        return passenger;
    }
}
