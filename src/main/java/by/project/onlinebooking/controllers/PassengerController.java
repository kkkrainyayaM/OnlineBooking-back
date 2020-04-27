package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.PassengerDto;
import by.project.onlinebooking.services.PassengerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @ApiOperation(value = "Get list of passengers of route")
    @GetMapping("/flights/{id}/passengers")
    public List<PassengerDto> getPassengersOfRoute(@PathVariable long id) {
        return passengerService.getAllByRouteId( id );
    }

    @ApiOperation(value = "Get list of routes of passenger")
    @GetMapping("/passenger/{id}/flights")
    public List<PassengerDto> getRoutesOfPassenger(@PathVariable long id) {
        return passengerService.getAllByRouteId( id );
    }

    @ApiOperation(value = "Add a passenger")
    @PostMapping("/passengers")
    public PassengerDto newPassenger(@Valid @RequestBody PassengerDto passenger) {
        return passengerService.add( passenger );
    }

    @ApiOperation(value = "Delete a passenger by user id")
    @DeleteMapping("/passengers/user")
    public void deletePassengerByUserId(@RequestParam(value = "id") long id) {
        passengerService.deleteByUserId( id );
    }

    @ApiOperation(value = "Delete a passenger by flight id")
    @DeleteMapping("/passengers/flight")
    public void deletePassengerByRouteId(@RequestParam(value = "id") long id) {
        passengerService.deleteByRouteId( id );
    }

    @ApiOperation(value = "Update a passenger")
    @PutMapping("/passengers")
    public PassengerDto updatePassenger(@Valid @RequestBody PassengerDto passenger) {
        return passengerService.update( passenger );
    }
}
