package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.mappers.PassengerMapper;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengersRepository passengerRepository;
    private final UserRepository userRepository;
    private final PassengerMapper passengerMapper;
    private final RouteRepository routeRepository;

    @Override
    public PassengerDto add(PassengerDto newPassenger) {
        Passenger passenger = passengerMapper.passengerDtoToPassenger(newPassenger);
        log.info("Passenger from passengerMapper: {}", passenger);
        Passenger passengerWithId = passengerRepository.save(passenger);
        log.info("Saved passenger: {}", passengerWithId);
        return passengerMapper.passengerToPassengerDto(passengerWithId);
    }

    @Override
    public void deleteByUserId(long id) {
        passengerRepository.deleteByUserId(id);
        log.info("Deleted passenger with userId={}", id);
    }

    @Override
    public void deleteByRouteId(long id) {
        passengerRepository.deleteByRouteId(id);
        log.info("Deleted passenger with routeId={}", id);
    }

    @Override
    public List<PassengerDto> getAllByUserId(long id) {
        log.info("UserId = {}", id);
        List<Passenger> list = passengerRepository.getAllByUserId(id);
        log.info("List of passengers by userId: {}", list);
        List<PassengerDto> listDto = list.stream()
                .map(passenger -> passengerMapper.passengerToPassengerWithRouteDto(
                        passenger, routeRepository.findById(passenger.getRouteId()).get()))
                .collect(Collectors.toList());
        log.info("List of passengers Dto : {}", listDto);
        return listDto;
    }

    @Override
    public List<PassengerDto> getAllByRouteId(long id) {
        log.info("RouteId = {}", id);
        List<Passenger> list = passengerRepository.getAllByRouteId(id);
        log.info("List of passengers by userId: {}", list);
        List<PassengerDto> listDto = list.stream()
                .map(passenger -> passengerMapper.passengerToPassengerWithUserDto(
                        passenger, userRepository.findById(passenger.getUserId()).get()))
                .collect(Collectors.toList());
        log.info("List of passengers by routeId: {}", listDto);
        return listDto;
    }

    @Override
    public PassengerDto update(PassengerDto passenger) {
        passengerRepository.save(passengerMapper.passengerDtoToPassenger(passenger));
        log.info("Updated passenger: {}", passenger);
        return passenger;
    }
}
