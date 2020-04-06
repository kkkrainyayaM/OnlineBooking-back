package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.DTO.PassengerDTO;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.mappers.PassengerMapper;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.services.PassengerService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("PassengerService")
public class PassengerServiceImpl implements PassengerService {

    private final PassengersRepository passengerRepository;
    private final PassengerMapper mapper = Mappers.getMapper( PassengerMapper.class );

    public PassengerServiceImpl(PassengersRepository passengersRepository) {
        this.passengerRepository = passengersRepository;
    }

    @Override
    public PassengerDTO add(PassengerDTO passenger) {
        Passenger passengerPOJO = mapper.passengerDtoToPassenger( passenger );
        return mapper.passengerToPassengerDto( passengerRepository.save( passengerPOJO ) );
    }

    @Override
    public void deleteByUserId(long id) {
        passengerRepository.findAll().stream()
                .filter( pas -> pas.getIdUser() == id )
                .forEach( passengerRepository::delete );
    }

    @Override
    public void deleteByRouteId(long id) {
        passengerRepository.findAllById( Collections.singleton( id ) )
                .forEach( passengerRepository::delete );
    }

    @Override
    public List<PassengerDTO> getAllByUserId(long id) {
        return passengerRepository.findAll().stream()
                .filter( passenger -> passenger.getIdUser() == id )
                .map( mapper::passengerToPassengerDto )
                .collect( Collectors.toList() );
    }

    @Override
    public List<PassengerDTO> getAllByRouteId(long id) {
        return passengerRepository.findAllById( Collections.singleton( id ) )
                .stream().map( mapper::passengerToPassengerDto )
                .collect( Collectors.toList() );
    }

    @Override
    public PassengerDTO update(PassengerDTO passenger) {
        Passenger updatedPassenger = passengerRepository.getOne( passenger.getRouteId() );
        updatedPassenger.setPointArrival( passenger.getArrivalPoint() );
        updatedPassenger.setPointDeparture( passenger.getDeparturePoint() );
        return mapper.passengerToPassengerDto( passengerRepository.save( updatedPassenger ) );

    }
}

