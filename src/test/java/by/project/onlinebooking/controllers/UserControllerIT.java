package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.UserDto;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.helpers.UserGenerator;
import by.project.onlinebooking.repositories.UserRepository;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserControllerIT {

    @Autowired
    UserRepository userRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private static final int PORT = 8080;
    private static final String BASE_URL = "http://localhost:";
    private static final int MIN_SIZE = 1;
    private static final long ID = 1L;

    @Test
    public void addUserWithSuccessStatusTest() {
        UserDto newUser = UserGenerator.generateDto();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        HttpEntity<UserDto> request = new HttpEntity<>( newUser, headers );

        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .postForEntity( BASE_URL + PORT + "/users", request, UserDto.class );


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
        System.out.println( userRepository.findAll() );

        this.restTemplate.delete( BASE_URL + PORT + "/users/{id}", ID );

        Optional<User> op = userRepository.findById( ID );
        assertFalse( op.isPresent() );

    }

    @Test
    public void updateUserWithSuccessStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        UserDto userDto = UserGenerator.generateDto();
        userDto.setPhone( "new" );

        System.out.println( userRepository.findById( ID ) );
        this.restTemplate.put( BASE_URL + PORT + "/users", userDto );

        assertEquals( userDto.getPhone(), userRepository.findById( ID ).get().getPhone() );
    }

    @Test
    public void getAllUsersWithSuccessStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        ResponseEntity<List> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + PORT + "/users", List.class );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( MIN_SIZE, responseEntity.getBody().size() );
    }

    @Test
    public void getUserWithSuccessStatusTest() {
        User user = UserGenerator.generate();
        userRepository.save( user );

        ResponseEntity<UserDto> responseEntity = this.restTemplate
                .getForEntity( BASE_URL + PORT + "/users/{id}", UserDto.class, ID );

        assertEquals( HttpStatus.OK, responseEntity.getStatusCode() );
        assertEquals( ID, responseEntity.getBody().getId() );
    }
}
