package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.Role;
import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import by.project.onlinebooking.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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

    @Test
    public void addPassengerWithSuccessStatusTest() {

        PassengerDto newPassenger = new PassengerDto();
        newPassenger.setId( 1 );
        newPassenger.setUserId( 1 );
        newPassenger.setRouteId( 1 );
        newPassenger.setArrivalPoint( "arrPoint" );
        newPassenger.setDeparturePoint( "depPoint" );

        User user = new User();
        user.setId( 1 );
        user.setFirstName( "firstName" );
        user.setLastName( "lastName" );
        user.setPassword( "password" );
        user.setRole( Role.USER );
        user.setPhone( "phone" );

        Date dateNow = new Date();
        Route route = new Route();
        route.setId( 1 );
        route.setDate( dateNow );
        route.setArrivalPoint( "arrivalPoint" );
        route.setDeparturePoint( "departurePoint" );
        route.setDepartureTime( "11:00" );
        route.setArrivalTime( "11:00" );

        userRepository.save( user );
        routeRepository.save( route );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<PassengerDto> request = new HttpEntity<PassengerDto>( newPassenger, headers );

        ResponseEntity<PassengerDto> responseEntity = this.restTemplate
                .postForEntity( BASE_URL + PORT + "/passengers", request, PassengerDto.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( 1L, responseEntity.getBody().getId() );
        assertEquals( "arrPoint", responseEntity.getBody().getArrivalPoint() );
        assertEquals( "depPoint", responseEntity.getBody().getDeparturePoint() );
        assertEquals( 1L, responseEntity.getBody().getUserId() );
        assertEquals( 1L, responseEntity.getBody().getRouteId() );

        Optional<Passenger> op = passengersRepository.findById( responseEntity.getBody().getId() );
        assertTrue( op.isPresent() );
    }

    @Test
    public void getPassengersOfRouteWithSuccessStatusTest() {
    }

    @Test
    public void getRoutesOfPassengerWithSuccessStatusTest() {
    }

    @Test
    public void deletePassengerByUserIdWithSuccessStatusTest() {
    }

    @Test
    public void deletePassengerByRouteIdWithSuccessStatusTest() {
    }

    @Test
    public void updatePassengerWithSuccessStatusTest() {
    }

}
