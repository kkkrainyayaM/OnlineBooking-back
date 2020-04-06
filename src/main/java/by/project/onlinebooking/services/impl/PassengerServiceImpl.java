package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.mappers.PassengerMapper;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.services.PassengerService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("PassengerService")
public class PassengerServiceImpl implements PassengerService {

    private final PassengersRepository passengerRepository;

    public PassengerServiceImpl(PassengersRepository passengersRepository) {
        this.passengerRepository = passengersRepository;
    }

    @Override
    public PassengerDto add(PassengerDto passenger) {
        Passenger passengerPOJO = PassengerMapper.INSTANCE.passengerDtoToPassenger( passenger );
        return PassengerMapper.INSTANCE.passengerToPassengerDto( passengerRepository.save( passengerPOJO ) );
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
    public List<PassengerDto> getAllByUserId(long id) {
        return passengerRepository.findAll().stream()
                .filter( passenger -> passenger.getIdUser() == id )
                .map( PassengerMapper.INSTANCE::passengerToPassengerDto )
                .collect( Collectors.toList() );
    }

    @Override
    public List<PassengerDto> getAllByRouteId(long id) {
        return passengerRepository.findAllById( Collections.singleton( id ) )
                .stream().map( PassengerMapper.INSTANCE::passengerToPassengerDto )
                .collect( Collectors.toList() );
    }

    @Override
    public PassengerDto update(PassengerDto passenger) {
        Passenger updatedPassenger = passengerRepository.getOne( passenger.getRouteId() );
        updatedPassenger.setArrivalPoint( passenger.getArrivalPoint() );
        updatedPassenger.setDeparturePoint( passenger.getDeparturePoint() );
        return PassengerMapper.INSTANCE.passengerToPassengerDto( passengerRepository.save( updatedPassenger ) );

    }
}

