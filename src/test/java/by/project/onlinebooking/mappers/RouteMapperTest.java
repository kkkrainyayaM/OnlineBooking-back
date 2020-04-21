package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.helpers.RouteGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

public class RouteMapperTest {

    private final RouteMapper routeMapper = Mappers.getMapper( RouteMapper.class );

    @Test
    public void routeToTdoMapping() {
        Route route = RouteGenerator.generate();

        RouteDto RouteDto = routeMapper.routeToRouteDto( route );

        Assert.assertEquals( route.getId(), RouteDto.getId() );
        Assert.assertEquals( route.getDate(), RouteDto.getDate() );
        Assert.assertEquals( route.getDeparturePoint(), RouteDto.getDeparturePoint() );
        Assert.assertEquals( route.getArrivalPoint(), RouteDto.getArrivalPoint() );
        Assert.assertEquals( route.getArrivalTime(), RouteDto.getArrivalTime() );
        Assert.assertEquals( route.getDepartureTime(), RouteDto.getDepartureTime() );
    }

    @Test
    public void tdoToRouteMapping() {
        RouteDto routeDto = RouteGenerator.generateDto();

        Route route = routeMapper.routeDtoToRoute( routeDto );

        Assert.assertEquals( route.getId(), routeDto.getId() );
        Assert.assertEquals( route.getDate(), routeDto.getDate() );
        Assert.assertEquals( route.getDeparturePoint(), routeDto.getDeparturePoint() );
        Assert.assertEquals( route.getArrivalPoint(), routeDto.getArrivalPoint() );
        Assert.assertEquals( route.getArrivalTime(), routeDto.getArrivalTime() );
        Assert.assertEquals( route.getDepartureTime(), routeDto.getDepartureTime() );
    }
}
