package by.project.onlinebooking.helpers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;

public class PassengerGenerator {

    public static Passenger generate() {
        Passenger passenger = new Passenger();
        passenger.setId( 1 );
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( 1 );
        passenger.setUserId( 1 );
        return passenger;
    }

    public static PassengerDto generateDto() {
        PassengerDto passenger = new PassengerDto();
        passenger.setId( 1 );
        passenger.setDeparturePoint( "departurePoint" );
        passenger.setArrivalPoint( "arrivalPoint" );
        passenger.setRouteId( 1 );
        passenger.setUserId( 1 );
        return passenger;
    }
}
