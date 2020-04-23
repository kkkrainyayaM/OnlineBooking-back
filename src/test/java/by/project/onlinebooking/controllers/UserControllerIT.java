package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.UserGenerator;
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
public class UserControllerIT {

    private static final String BASE_URL = "http://localhost:";
    private static final int MIN_SIZE = 1;
    private static final long ID = 1L;
    private static final long SEC_ID = 2L;

    @LocalServerPort
    private int port;

    @Autowired
    UserRepository userRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();



    @Test
    public void addUserWithSuccessStatusTest() {
        UserDto newUser = UserGenerator.generateDto();
        newUser.setId( SEC_ID );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<UserDto> request = new HttpEntity<>( newUser, headers );

        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .postForEntity( BASE_URL + port + "/users", request, UserDto.class );


        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertNotNull( responseEntity.getBody() );
        val user = responseEntity.getBody();
        assertEquals( newUser.getId(), user.getId() );
        assertEquals( newUser.getFirstName(), user.getFirstName() );
        assertEquals( newUser.getLastName(), user.getLastName() );
        assertEquals( newUser.getPhone(), user.getPhone() );

        Optional<User> op = userRepository.findById( responseEntity.getBody().getId() );
        assertTrue( op.isPresent() );
    }

    @Test
    public void deleteUserWithSuccessStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        this.restTemplate.delete( BASE_URL + port + "/users/{id}", SEC_ID );

        Optional<User> op = userRepository.findById( SEC_ID );
        assertFalse( op.isPresent() );

    }

    @Test
    public void deleteUserWithFailedStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        this.restTemplate.delete( BASE_URL + port + "/users/{id}", 3 );

        Optional<User> op = userRepository.findById( SEC_ID );
        assertTrue( op.isPresent() );

        userRepository.deleteById( SEC_ID );

    }

    @Test
    public void updateUserWithSuccessStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        UserDto userDto = UserGenerator.generateDto();
        userDto.setId( SEC_ID );
        userDto.setPhone( "new" );

        System.out.println( userRepository.findById( SEC_ID ) );
        this.restTemplate.put( BASE_URL + port + "/users", userDto );

        assertEquals( userDto.getPhone(), userRepository.findById( SEC_ID ).get().getPhone() );
    }

    @Test
    public void getAllUsersWithSuccessStatusTest() {
        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + port + "/users", List.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( MIN_SIZE, responseEntity.getBody().size() );
    }

    @Test
    public void getUserWithSuccessStatusTest() {
        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + port + "/users/{id}", UserDto.class, ID );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( ID, responseEntity.getBody().getId() );
    }

    @Test
    public void getUserWithFailedStatusTest() {
        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + port + "/users/{id}", UserDto.class, 2 );

        assertEquals( HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode() );
    }
}
