package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.PassengerGenerator;
import by.project.onlinebooking.helpers.RouteGenerator;
import by.project.onlinebooking.helpers.UserGenerator;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
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
    private static final int EMPTY_LIST = 0;
    private static final long ID = 1L;

    @LocalServerPort
    private int port;

    @Autowired
    PassengersRepository passengersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setSource() {
        Passenger passenger = PassengerGenerator.generate();
        User user = UserGenerator.generate();
        Route route = RouteGenerator.generate();

        passengersRepository.deleteAll();
        userRepository.deleteAll();
        routeRepository.deleteAll();
        log.info( "userRepository: {}\n", userRepository.findAll() );
        log.info( "passengerRepository: {}\n", passengersRepository.findAll() );
        log.info( "routeRepository: {}\n", routeRepository.findAll() );
        userRepository.save( user );
        routeRepository.save( route );
        passengersRepository.save( passenger );

        log.info( "userRepository: {}\n", userRepository.findAll() );
        log.info( "passengerRepository: {}\n", passengersRepository.findAll() );
        log.info( "routeRepository: {}\n", routeRepository.findAll() );
    }

    @Test
    public void addPassengerWithSuccessStatusTest() {
        passengersRepository.deleteAll();
        PassengerDto newPassenger = PassengerGenerator.generateDto();

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
        System.out.println( passengersRepository.getAllByUserId( ID ) );

        this.restTemplate.delete( BASE_URL + port + "/passengers/user/{id}", ID );
        System.out.println( passengersRepository.getAllByUserId( ID ) );
        assertEquals( EMPTY_LIST, passengersRepository.getAllByUserId( ID ).size() );
    }

    @Test
    public void deletePassengerByRouteIdWithSuccessStatusTest() {
        System.out.println( passengersRepository.getAllByUserId( ID ) );

        this.restTemplate.delete( BASE_URL + port + "/passengers/flight/{id}", ID );
        System.out.println( passengersRepository.getAllByRouteId( ID ) );
        assertEquals( EMPTY_LIST, passengersRepository.getAllByRouteId( ID ).size() );

    }

    @Test
    public void updatePassengerWithSuccessStatusTest() {
        PassengerDto passengerDto = PassengerGenerator.generateDto();
        passengerDto.setArrivalPoint( "new" );

        System.out.println( passengersRepository.getAllByUserId( ID ) );
        this.restTemplate.put( BASE_URL + port + "/passengers", passengerDto );

        assertEquals( passengerDto.getArrivalPoint(), passengersRepository.findById( ID ).get().getArrivalPoint() );
    }
}
