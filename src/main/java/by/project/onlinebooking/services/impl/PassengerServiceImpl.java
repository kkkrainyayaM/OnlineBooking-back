package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.dto.PassengerWithRouteDto;
import by.project.onlinebooking.dto.PassengerWithUserDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.mappers.PassengerMapper;
import by.project.onlinebooking.mappers.PassengerWithRouteMapper;
import by.project.onlinebooking.mappers.PassengerWithUserMapper;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("PassengerService")
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengersRepository passengerRepository;
    private final UserRepository userRepository;
    private final PassengerMapper passengerMapper;
    private final RouteRepository routeRepository;
    private final PassengerWithUserMapper passengerWithUserMapper;
    private final PassengerWithRouteMapper passengerWithRouteMapper;

    @Override
    public PassengerDto add(PassengerDto passenger) {
        Passenger passengerPOJO = passengerMapper.passengerDtoToPassenger( passenger );
        return passengerMapper.passengerToPassengerDto( passengerRepository.save( passengerPOJO ) );
    }

    @Override
    public void deleteByUserId(long id) {
        passengerRepository.findAll().stream()
                .filter( pas -> pas.getUserId() == id )
                .forEach( passengerRepository::delete );
    }

    @Override
    public void deleteByRouteId(long id) {
        passengerRepository.findAllById( Collections.singleton( id ) )
                .forEach( passengerRepository::delete );
    }

    @Override
    public List<PassengerWithRouteDto> getAllByUserId(long id) {
        return passengerRepository.findAll().stream()
                .filter( passenger -> passenger.getUserId() == id )
                .map( passenger -> passengerWithRouteMapper
                        .passengerToPassengerWithRouteDto( passenger, routeRepository.getOne( passenger.getRouteId() ) ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<PassengerWithUserDto> getAllByRouteId(long id) {
        return passengerRepository.findAll().stream()
                .filter( passenger -> passenger.getUserId() == id )
                .map( passenger -> passengerWithUserMapper
                        .passengerToPassengerWithUserDto( passenger, userRepository.getOne( passenger.getUserId() ) ) )
                .collect( Collectors.toList() );
    }

    @Override
    public PassengerDto update(PassengerDto passenger) {
        Passenger updatedPassenger = passengerRepository.getOne( passenger.getRouteId() );
        updatedPassenger.setArrivalPoint( passenger.getArrivalPoint() );
        updatedPassenger.setDeparturePoint( passenger.getDeparturePoint() );
        return passengerMapper.passengerToPassengerDto( passengerRepository.save( updatedPassenger ) );

    }
}

