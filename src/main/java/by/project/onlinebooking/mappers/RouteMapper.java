package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.RouteDTO;
import by.project.onlinebooking.entities.Route;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteDTO routeToRouteDto(Route route);

    Route routeDtoToRoute(RouteDTO routeDTO);
}
