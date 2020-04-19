package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerWithUserDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PassengerWithUserMapper {

    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "passenger.id", target = "id")
    @Mapping(source = "passenger.departurePoint", target = "departurePoint")
    @Mapping(source = "passenger.arrivalPoint", target = "arrivalPoint")
    PassengerWithUserDto passengerToPassengerWithUserDto(Passenger passenger, User user);
}
