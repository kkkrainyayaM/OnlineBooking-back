package by.project.onlinebooking.mappers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PassengerMapper {

    PassengerMapper INSTANCE = Mappers.getMapper( PassengerMapper.class );

    PassengerDto passengerToPassengerDto(Passenger passenger);

    Passenger passengerDtoToPassenger(PassengerDto passengerDTO);
}
