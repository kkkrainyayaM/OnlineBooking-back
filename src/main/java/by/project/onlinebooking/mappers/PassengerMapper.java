package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.PassengerDTO;
import by.project.onlinebooking.entities.Passenger;
import org.mapstruct.Mapper;

@Mapper
public interface PassengerMapper {

    PassengerDTO passengerToPassengerDto(Passenger passenger);
}
