package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper( RouteMapper.class );

    RouteDto routeToRouteDto(Route route);

    Route routeDtoToRoute(RouteDto routeDTO);
}
