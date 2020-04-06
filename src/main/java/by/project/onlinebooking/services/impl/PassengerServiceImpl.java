package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.exceptions.PassengerNotFoundException;
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
    public Passenger add(Passenger passenger) {
        return passengerRepository.save( passenger );
    }

    @Override
    public void deleteByUserId(long id) throws PassengerNotFoundException {
        passengerRepository.findAll().stream()
                .filter( pas -> pas.getIdUser() == id )
                .forEach( passengerRepository::delete );
    }

    @Override
    public void deleteByRouteId(long id) throws PassengerNotFoundException {
        passengerRepository.findAllById( Collections.singleton( id ) ).forEach( passengerRepository::delete );
    }

    @Override
    public List<Passenger> getAllByUserId(long id) {
        return passengerRepository.findAll().stream()
                .filter( passenger -> passenger.getIdUser() == id )
                .collect( Collectors.toList() );
    }

    @Override
    public List<Passenger> getAllByRouteId(long id) {
        return passengerRepository.findAllById( Collections.singleton( id ) );
    }

    @Override
    public Passenger update(Passenger passenger) throws PassengerNotFoundException {
        Passenger updatedPassenger = passengerRepository.findById( passenger.getIdRoute() )
                .orElseThrow( () -> new PassengerNotFoundException( passenger.getIdRoute() ) );
        updatedPassenger.setPointArrival( passenger.getPointArrival() );
        updatedPassenger.setPointDeparture( passenger.getPointDeparture() );
        return passengerRepository.save( updatedPassenger );

    }
}

