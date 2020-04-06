package by.project.onlinebooking.mappers;

import by.project.onlinebooking.DTO.PassengerDTO;
import by.project.onlinebooking.entities.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerMapper INSTANCE = Mappers.getMapper( PassengerMapper.class );

    PassengerDTO passengerToPassengerDto(Passenger passenger);

    Passenger passengerDtoToPassenger(PassengerDTO passengerDTO);
}
