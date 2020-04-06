package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.RouteDTO;
import by.project.onlinebooking.entities.Route;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class RouteMapperTest {


    @Test
    public void givenRouteToRouteTdo__whenMaps__thenCorrect() {
        Date dateNow = new Date();
        Route route = new Route();
        route.setId( 1 );
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "departureTime" );
        route.setArrivalTime( "arrivalTime" );

        RouteDTO routeDTO = RouteMapper.INSTANCE.routeToRouteDto( route );

        Assert.assertEquals( route.getId(), routeDTO.getId() );
        Assert.assertEquals( route.getDate(), routeDTO.getDate() );
        Assert.assertEquals( route.getDeparturePoint(), routeDTO.getDeparturePoint() );
        Assert.assertEquals( route.getArrivalPoint(), routeDTO.getArrivalPoint() );
        Assert.assertEquals( route.getArrivalTime(), routeDTO.getArrivalTime() );
        Assert.assertEquals( route.getDepartureTime(), routeDTO.getDepartureTime() );

    }

    @Test
    public void givenRouteTdoToRoute__whenMaps__thenCorrect() {
        Date dateNow = new Date();
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId( 1 );
        routeDTO.setDate( dateNow );
        routeDTO.setArrivalPoint( "arrivalPoint" );
        routeDTO.setDeparturePoint( "departurePoint" );
        routeDTO.setDepartureTime( "departureTime" );
        routeDTO.setArrivalTime( "arrivalTime" );

        Route route = RouteMapper.INSTANCE.routeDtoToRoute( routeDTO );

        Assert.assertEquals( route.getId(), routeDTO.getId() );
        Assert.assertEquals( route.getDate(), routeDTO.getDate() );
        Assert.assertEquals( route.getDeparturePoint(), routeDTO.getDeparturePoint() );
        Assert.assertEquals( route.getArrivalPoint(), routeDTO.getArrivalPoint() );
        Assert.assertEquals( route.getArrivalTime(), routeDTO.getArrivalTime() );
        Assert.assertEquals( route.getDepartureTime(), routeDTO.getDepartureTime() );
    }

}