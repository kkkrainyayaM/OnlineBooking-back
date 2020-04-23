package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.helpers.PassengerGenerator;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@IntegrationTest
@RunWith(SpringRunner.class)
@Transactional
public class PassengerControllerIT {

    private static final String BASE_URL = "http://localhost:";
    private static final int MIN_SIZE = 1;
    private static final long ID = 1L;
    private static final long SEC_ID = 2L;

    @LocalServerPort
    private int port;

    @Autowired
    PassengersRepository passengersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void addPassengerWithSuccessStatusTest() {
        log.info( "userRepository: {}\n", userRepository.findAll() );
        log.info( "passengerRepository: {}\n", passengersRepository.findAll() );
        log.info( "routeRepository: {}\n", routeRepository.findAll() );
        PassengerDto newPassenger = PassengerGenerator.generateDto( ID, ID );
        log.info( "userId: {}", newPassenger.getUserId() );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<PassengerDto> request = new HttpEntity<>( newPassenger, headers );

        ResponseEntity<PassengerDto> responseEntity = this.restTemplate
                .postForEntity( BASE_URL + port + "/passengers", request, PassengerDto.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        val passengerDto = responseEntity.getBody();
        assertNotNull( passengerDto );
        assertEquals( newPassenger.getId(), passengerDto.getId() );
        assertEquals( newPassenger.getArrivalPoint(), passengerDto.getArrivalPoint() );
        assertEquals( newPassenger.getDeparturePoint(), passengerDto.getDeparturePoint() );
        assertEquals( newPassenger.getUserId(), passengerDto.getUserId() );
        assertEquals( newPassenger.getRouteId(), passengerDto.getRouteId() );

        Optional<Passenger> op = passengersRepository.findById( responseEntity.getBody().getId() );
        assertTrue( op.isPresent() );

        passengersRepository.deleteById( op.get().getId() );
    }

    @Test
    public void getPassengersOfRouteWithSuccessStatusTest() {
        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + port + "/flights/{id}/passengers", List.class, ID );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( MIN_SIZE, responseEntity.getBody().size() );
    }

    @Test
    public void getRoutesOfPassengerWithSuccessStatusTest() {
        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + port + "/passenger/{id}/flights", List.class, ID );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( MIN_SIZE, responseEntity.getBody().size() );
    }

    @Test
    public void deletePassengerByUserIdWithSuccessStatusTest() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );
        passengersRepository.save( passenger );

        this.restTemplate.delete( BASE_URL + port + "/passengers/user/{id}", ID );
        System.out.println( passengersRepository.getAllByUserId( ID ) );
        assertEquals( MIN_SIZE, passengersRepository.getAllByUserId( ID ).size() );
    }

    @Test
    public void deletePassengerByRouteIdWithSuccessStatusTest() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );
        passengersRepository.save( passenger );

        this.restTemplate.delete( BASE_URL + port + "/passengers/flight/{id}", ID );
        System.out.println( passengersRepository.getAllByRouteId( ID ) );
        assertEquals( MIN_SIZE, passengersRepository.getAllByRouteId( ID ).size() );

    }

    @Test
    public void updatePassengerWithSuccessStatusTest() {
        Passenger passenger = PassengerGenerator.generate( ID, ID );
        passengersRepository.save( passenger );

        PassengerDto passengerDto = PassengerGenerator.generateDto( ID, ID );
        passengerDto.setId( SEC_ID );
        passengerDto.setArrivalPoint( "new" );

        this.restTemplate.put( BASE_URL + port + "/passengers", passengerDto );

        log.info( "userRepository: {}\n", userRepository.findAll() );
        log.info( "passengerRepository: {}\n", passengersRepository.findAll() );
        log.info( "routeRepository: {}\n", routeRepository.findAll() );
        assertEquals( passengerDto.getArrivalPoint(), passengersRepository.findById( SEC_ID ).get().getArrivalPoint() );
        passengersRepository.deleteById( SEC_ID );
    }

}
