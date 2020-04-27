package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Date;

public class RouteMapperTest {

    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);

    @Test
    public void routeToDtoMapping() {
        Date dateNow = new Date();
        Route route = new Route();
        route.setId(1);
        route.setDate(dateNow);
        route.setArrivalPoint("arrivalPoint");
        route.setDeparturePoint("departurePoint");
        route.setDepartureTime("departureTime");
        route.setArrivalTime("arrivalTime");

        RouteDto routeDto = routeMapper.routeToRouteDto(route);

        Assert.assertEquals(route.getId(), routeDto.getId());
        Assert.assertEquals(route.getDate(), routeDto.getDate());
        Assert.assertEquals(route.getDeparturePoint(), routeDto.getDeparturePoint());
        Assert.assertEquals(route.getArrivalPoint(), routeDto.getArrivalPoint());
        Assert.assertEquals(route.getArrivalTime(), routeDto.getArrivalTime());
        Assert.assertEquals(route.getDepartureTime(), routeDto.getDepartureTime());
    }

    @Test
    public void DtoToRouteMapping() {
        Date dateNow = new Date();
        RouteDto routeDto = new RouteDto();
        routeDto.setId(1);
        routeDto.setDate(dateNow);
        routeDto.setArrivalPoint("arrivalPoint");
        routeDto.setDeparturePoint("departurePoint");
        routeDto.setDepartureTime("departureTime");
        routeDto.setArrivalTime("arrivalTime");

        Route route = routeMapper.routeDtoToRoute(routeDto);

        Assert.assertEquals(route.getId(), routeDto.getId());
        Assert.assertEquals(route.getDate(), routeDto.getDate());
        Assert.assertEquals(route.getDeparturePoint(), routeDto.getDeparturePoint());
        Assert.assertEquals(route.getArrivalPoint(), routeDto.getArrivalPoint());
        Assert.assertEquals(route.getArrivalTime(), routeDto.getArrivalTime());
        Assert.assertEquals(route.getDepartureTime(), routeDto.getDepartureTime());
    }
}
