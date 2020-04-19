package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerDto passengerToPassengerDto(Passenger passenger);

    Passenger passengerDtoToPassenger(PassengerDto passengerDto);

    Passenger update(Passenger passenger);
}
