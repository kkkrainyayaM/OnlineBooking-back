package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerWithRouteDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerWithRouteMapper {

    @Mapping(source = "route.date", target = "date")
    @Mapping(source = "route.departureTime", target = "departureTime")
    @Mapping(source = "passenger.departurePoint", target = "departurePoint")
    @Mapping(source = "passenger.arrivalPoint", target = "arrivalPoint")
    PassengerWithRouteDto passengerToPassengerWithRouteDto(Passenger passenger, Route route);

}
