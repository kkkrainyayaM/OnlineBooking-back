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

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengersRepository passengerRepository;
    private final UserRepository userRepository;
    private final PassengerMapper passengerMapper;
    private final RouteRepository routeRepository;
    private final PassengerWithUserMapper passengerWithUserMapper;
    private final PassengerWithRouteMapper passengerWithRouteMapper;

    @Override
    public PassengerDto add(PassengerDto newPassenger) {
        Passenger passenger = passengerMapper.passengerDtoToPassenger( newPassenger );
        return passengerMapper.passengerToPassengerDto( passengerRepository.save( passenger ) );
    }

    @Override
    public void deleteByUserId(long id) {
        passengerRepository.deleteByUserId( id );
    }

    @Override
    public void deleteByRouteId(long id) {
        passengerRepository.deleteByRouteId( id );
    }

    @Override
    public List<PassengerWithRouteDto> getAllByUserId(long id) {
        return passengerRepository.getAllByUserId( id ).stream()
                .map( passenger -> passengerWithRouteMapper
                        .passengerToPassengerWithRouteDto( passenger, routeRepository.findById( passenger.getRouteId() )
                                .get() ) )
                .collect( Collectors.toList() );
    }

    @Override
    public List<PassengerWithUserDto> getAllByRouteId(long id) {
        return passengerRepository.getAllByRouteId( id ).stream()
                .map( passenger -> passengerWithUserMapper
                        .passengerToPassengerWithUserDto( passenger, userRepository.findById( passenger.getUserId() )
                                .get() ) )
                .collect( Collectors.toList() );
    }

    @Override
    public PassengerDto update(PassengerDto passenger) {
        passengerRepository.save( passengerMapper.passengerDtoToPassenger( passenger ) );
        return passenger;
    }
}
