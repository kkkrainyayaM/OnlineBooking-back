package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.entities.User;
import by.project.onlinebooking.exceptions.UserNotFoundException;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class PassengerController {

    @Autowired
    private PassengersRepository passengersRepository;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Get list of passengers")
    @GetMapping("/admin/flights/{id}/passengers")
    public List<User> getPassengers(@PathVariable long id) {
        List<User> passengers = new ArrayList<>();
        passengersRepository.findAllById( Collections.singleton( id ) )
                .forEach( passenger -> passengers.add( userRepository
                        .findById( passenger.getIdUser() ).get() ) );
        return passengers;
    }

    @ApiOperation(value = "Add a passenger")
    @PostMapping("/account/book")
    public Passenger newPassenger(@RequestBody Passenger passenger) {
        return passengersRepository.save( passenger );
    }

    @ApiOperation(value = "Delete a passenger")
    @DeleteMapping("/account/{idUser}/flight/{idRoute}")
    public void deletePassenger(@PathVariable(value = "idUser") long userId, @PathVariable(value = "idRoute") long routeId)
            throws UserNotFoundException {
        passengersRepository.findAllById( Collections.singleton( routeId ) )
                .stream().filter( passenger -> passenger.getIdUser() == userId )
                .forEach( passenger -> passengersRepository.delete( passenger ) );
    }
}
