package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.RouteDTO;
import by.project.onlinebooking.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper( RouteMapper.class );

    RouteDTO routeToRouteDto(Route route);

    Route routeDtoToRoute(RouteDTO routeDTO);
}
