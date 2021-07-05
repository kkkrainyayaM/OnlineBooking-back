package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerDto passengerToPassengerDto(Passenger passenger);

    Passenger passengerDtoToPassenger(PassengerDto passengerDto);

    @Mapping(source = "route.date", target = "date")
    @Mapping(source = "route.departureTime", target = "departureTime")
    @Mapping(source = "passenger.id", target = "id")
    @Mapping(source = "passenger.departurePoint", target = "departurePoint")
    @Mapping(source = "passenger.arrivalPoint", target = "arrivalPoint")
    PassengerDto passengerToPassengerWithRouteDto(Passenger passenger, Route route);

    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "passenger.id", target = "id")
    @Mapping(source = "passenger.departurePoint", target = "departurePoint")
    @Mapping(source = "passenger.arrivalPoint", target = "arrivalPoint")
    PassengerDto passengerToPassengerWithUserDto(Passenger passenger, User user);

    Passenger update(Passenger passenger);
}
