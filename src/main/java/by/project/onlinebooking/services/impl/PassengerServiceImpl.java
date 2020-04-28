package by.project.onlinebooking.services.impl;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.mappers.PassengerMapper;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import by.project.onlinebooking.services.PassengerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
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
    public PassengerDto add(PassengerDto passengerDto) {
        log.info("Save passenger: {}", passengerDto);
        val passenger = passengerMapper.passengerDtoToPassenger(passengerDto);
        val savedPassenger = passengerRepository.save(passenger);
        return passengerMapper.passengerToPassengerDto(savedPassenger);
    }

    @Override
    public void deleteByUserId(long id) {
        passengerRepository.deleteAllByUserId(id);
        log.info("Delete passenger with userId={}", id);
    }

    @Override
    public void deleteByRouteId(long id) {
        passengerRepository.deleteAllByRouteId(id);
        log.info("Delete passenger with routeId={}", id);
    }

    @Override
    public List<PassengerDto> getAllByUserId(long id) {
        log.info("UserId = {}", id);
        val list = passengerRepository.getAllByUserId(id);
        log.info("Get list of passengers by userId: {}", list);
        return list.stream()
                .map(passenger -> passengerMapper.passengerToPassengerWithRouteDto(
                        passenger, routeRepository.findById(passenger.getRouteId()).get()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PassengerDto> getAllByRouteId(long id) {
        log.info("RouteId = {}", id);
        val list = passengerRepository.getAllByRouteId(id);
        log.info("Get list of passengers by routeId: {}", list);
        return list.stream()
                .map(passenger -> passengerMapper.passengerToPassengerWithUserDto(
                        passenger, userRepository.findById(passenger.getUserId()).get()))
                .collect(Collectors.toList());
    }

    @Override
    public PassengerDto update(PassengerDto passenger) {
        passengerRepository.save(passengerMapper.passengerDtoToPassenger(passenger));
        log.info("Update passenger: {}", passenger);
        return passenger;
    }
}
