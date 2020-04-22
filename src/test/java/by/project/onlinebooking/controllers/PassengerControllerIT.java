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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration
public class PassengerControllerIT {

    @Autowired
    PassengersRepository passengersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RouteRepository routeRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private static final int PORT = 8080;
    private static final String BASE_URL = "http://localhost:";

    @Before
    public void setSource() {
        Passenger passenger = PassengerGenerator.generate();
        User user = UserGenerator.generate();
        Route route = RouteGenerator.generate();

        passengersRepository.deleteAll();
        userRepository.deleteAll();
        routeRepository.deleteAll();
        userRepository.save( user );
        routeRepository.save( route );
        passengersRepository.save( passenger );
    }

    @Test
    public void addPassengerWithSuccessStatusTest() {
        passengersRepository.deleteAll();
        PassengerDto newPassenger = PassengerGenerator.generateDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<PassengerDto> request = new HttpEntity<PassengerDto>( newPassenger, headers );

        ResponseEntity<PassengerDto> responseEntity = this.restTemplate
                .postForEntity( BASE_URL + PORT + "/passengers", request, PassengerDto.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( 1L, responseEntity.getBody().getId() );
        assertEquals( "arrivalPoint", responseEntity.getBody().getArrivalPoint() );
        assertEquals( "departurePoint", responseEntity.getBody().getDeparturePoint() );
        assertEquals( 1L, responseEntity.getBody().getUserId() );
        assertEquals( 1L, responseEntity.getBody().getRouteId() );

        Optional<Passenger> op = passengersRepository.findById( responseEntity.getBody().getId() );
        assertTrue( op.isPresent() );
    }

    @Test
    public void getPassengersOfRouteWithSuccessStatusTest() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> request = new HttpEntity<>( null, headers );

        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + PORT + "/flights/1/passengers", List.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( 1, responseEntity.getBody().size() );
    }

    @Test
    public void getRoutesOfPassengerWithSuccessStatusTest() {
        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + PORT + "/passenger/1/flights", List.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( 1, responseEntity.getBody().size() );
    }

    @Test
    public void deletePassengerByUserIdWithSuccessStatusTest() {
        System.out.println( passengersRepository.getAllByUserId( 1L ) );

        this.restTemplate.delete( BASE_URL + PORT + "/passengers/user/{id}", 1L );
        System.out.println( passengersRepository.getAllByUserId( 1L ) );
        assertEquals( 0, passengersRepository.getAllByUserId( 1L ).size() );
    }

    @Test
    public void deletePassengerByRouteIdWithSuccessStatusTest() {
        System.out.println( passengersRepository.getAllByUserId( 1L ) );

        this.restTemplate.delete( BASE_URL + PORT + "/passengers/flight/{id}", 1L );
        System.out.println( passengersRepository.getAllByRouteId( 1L ) );
        assertEquals( 0, passengersRepository.getAllByRouteId( 1L ).size() );

    }

    @Test
    public void updatePassengerWithSuccessStatusTest() {
        PassengerDto passengerDto = PassengerGenerator.generateDto();
        passengerDto.setArrivalPoint( "new" );

        System.out.println( passengersRepository.getAllByUserId( 1L ) );
        this.restTemplate.put( BASE_URL + PORT + "/passengers", passengerDto );

        assertEquals( passengerDto.getArrivalPoint(), passengersRepository.findById( 1L ).get().getArrivalPoint() );
    }
}
