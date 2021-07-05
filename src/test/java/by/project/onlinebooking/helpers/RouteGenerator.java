package by.project.onlinebooking.helpers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;

import java.time.LocalDate;

public class RouteGenerator {

    public static Route generate() {
        LocalDate dateNow = LocalDate.now();
        Route route = new Route();
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "11:00" );
        route.setArrivalTime( "11:00" );
        return route;
    }

    public static RouteDto generateDto() {
        LocalDate dateNow = LocalDate.now();
        RouteDto route = new RouteDto();
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "11:00" );
        route.setArrivalTime( "11:00" );
        return route;
    }
}
