package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteDto routeToRouteDto(Route route);

    Route routeDtoToRoute(RouteDto routeDto);
}
