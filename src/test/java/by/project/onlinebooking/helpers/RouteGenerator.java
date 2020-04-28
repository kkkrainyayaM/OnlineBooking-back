package by.project.onlinebooking.helpers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;

import java.util.Date;

public class RouteGenerator {

    public static Route generate() {
        Date dateNow = new Date();
        Route route = new Route();
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "11:00" );
        route.setArrivalTime( "11:00" );
        return route;
    }

    public static RouteDto generateDto() {
        Date dateNow = new Date();
        RouteDto route = new RouteDto();
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "11:00" );
        route.setArrivalTime( "11:00" );
        return route;
    }
}
