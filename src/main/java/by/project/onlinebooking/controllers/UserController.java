package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.exceptions.UserNotFoundException;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengersRepository passengersRepository;

    @GetMapping("/admin/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/admin/flights/{id}")
    public List<User> getPassengers(@PathVariable long id) {
        List<User> passengers = new ArrayList<>();
        passengersRepository.findAllById( Collections.singleton( id ) )
                .forEach( passenger -> passengers.add( userRepository
                .findById( passenger.getIdUser() ).get() ) );
        return passengers;
    }

    @PostMapping("/user")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save( newUser );
    }

    @GetMapping("/account/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.findById( id )
                .orElseThrow( () -> new UserNotFoundException( id ) );
    }

    @PutMapping("/account/{id}")
    public User updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails)
            throws UserNotFoundException {
        User user = userRepository.findById( userId )
                .orElseThrow( () -> new UserNotFoundException( userId ) );
        user.setFirstName( userDetails.getFirstName() );
        user.setLastName( userDetails.getLastName() );
        user.setPassword( userDetails.getPassword() );
        user.setPhone( userDetails.getPhone() );
        return userRepository.save( user );
    }

    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable(value = "id") Long userId)
            throws UserNotFoundException {
        User user = userRepository.findById( userId )
                .orElseThrow( () -> new UserNotFoundException( userId ) );
        userRepository.delete( user );
    }
}
