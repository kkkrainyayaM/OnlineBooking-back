package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.exceptions.RouteNotFoundException;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PassengersRepository passengersRepository;

    @ApiOperation(value = "View a list of all flights", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/admin/flights")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @ApiOperation(value = "View a list of user's flights", response = List.class)
    @GetMapping("/account/{id}/flights")
    public List<Route> getUserRoutes(@PathVariable long id) {
        List<Route> routes = new ArrayList<>();
        passengersRepository.findAll().stream().filter( passenger -> passenger.getIdUser() == id )
                .forEach( passenger -> routes.add( routeRepository
                        .findById( passenger.getIdRoute() ).get() ) );
        return routes;
    }

    @ApiOperation(value = "Get a flight by Id")
    @GetMapping("/admin/flights/{id}")
    public Route getRoute(@PathVariable long id) {
        return routeRepository.findById( id )
                .orElseThrow( () -> new RouteNotFoundException( id ) );
    }

    @ApiOperation(value = "Add a flight")
    @PostMapping("/admin/flights")
    public Route newRoute(@RequestBody Route newRoute) {
        return routeRepository.save( newRoute );
    }

    @ApiOperation(value = "Update a flight")
    @PutMapping("/admin/flights/{id}")
    public Route updateRoute(
            @PathVariable(value = "id") Long routeId,
            @Valid @RequestBody Route routeDetails)
            throws RouteNotFoundException {
        Route route = routeRepository.findById( routeId )
                .orElseThrow( () -> new RouteNotFoundException( routeId ) );
        route.setDate( routeDetails.getDate() );
        route.setPointArrival( routeDetails.getPointArrival() );
        route.setPointDeparture( routeDetails.getPointDeparture() );
        route.setTimeArrival( routeDetails.getTimeArrival() );
        route.setTimeDeparture( routeDetails.getTimeDeparture() );
        return routeRepository.save( route );
    }

    @ApiOperation(value = "Delete a flight")
    @DeleteMapping("/admin/flight/{id}")
    public void deleteFlight(@PathVariable(value = "id") long routeId)
            throws RouteNotFoundException {
        passengersRepository.findAllById( Collections.singleton( routeId ) )
                .forEach( route -> passengersRepository.delete( route ) );
        Route route = routeRepository.findById( routeId )
                .orElseThrow( () -> new RouteNotFoundException( routeId ) );
        routeRepository.delete( route );
    }
}
