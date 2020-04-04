package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.repositories.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /*@GetMapping("/admin/flight/{id}")
    public List<User> getPassengers(@PathVariable(value = "id") Long flightId)
    throws ResourceNotFoundException {

    }*/

    @PutMapping("/account/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails)
            throws ResourceNotFoundException {
        User user = userRepository.findById( userId )
                .orElseThrow( () -> new ResourceNotFoundException( ("User not found for this id :: " + userId) ) );
        user.setFirstName( userDetails.getFirstName() );
        user.setLastName( userDetails.getLastName() );
        user.setPassword( userDetails.getPassword() );
        user.setPhone( userDetails.getPhone() );
        final User updatedUser = userRepository.save( user );
        return ResponseEntity.ok( updatedUser );
    }

    @DeleteMapping("/admin/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById( userId )
                .orElseThrow( () -> new ResourceNotFoundException( "User not found for this id :: " + userId ) );

        userRepository.delete( user );
        Map<String, Boolean> response = new HashMap<>();
        response.put( "deleted", Boolean.TRUE );
        return response;
    }
}
