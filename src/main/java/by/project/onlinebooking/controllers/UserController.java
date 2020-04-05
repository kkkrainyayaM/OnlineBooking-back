package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.exceptions.UserNotFoundException;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
@Api(value = "Online-booking System", description = "Operations pertaining to users and flights in Online-booking System")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PassengersRepository passengersRepository;

    @ApiOperation(value = "View a list of all users", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/admin/users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "Get a user by Id")
    @GetMapping("/account/{id}")
    public User getUser(@PathVariable long id) {
        return userRepository.findById( id )
                .orElseThrow( () -> new UserNotFoundException( id ) );
    }

    @ApiOperation(value = "Add a user")
    @PostMapping("/user")
    public User newUser(@RequestBody User newUser) {
        return userRepository.save( newUser );
    }

    @ApiOperation(value = "Update a user")
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

    @ApiOperation(value = "Delete a user")
    @DeleteMapping("/admin/users/{id}")
    public void deleteUser(@PathVariable(value = "id") long userId)
            throws UserNotFoundException {
        passengersRepository.findAll().stream().filter( passenger -> passenger.getIdUser() == userId )
                .forEach( passenger -> passengersRepository.delete( passenger ) );
        User user = userRepository.findById( userId )
                .orElseThrow( () -> new UserNotFoundException( userId ) );
        userRepository.delete( user );
    }


}
