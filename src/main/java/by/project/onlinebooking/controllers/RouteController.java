package by.project.onlinebooking.controllers;

import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.exceptions.RouteNotFoundException;
import by.project.onlinebooking.repositories.PassengersRepository;
import by.project.onlinebooking.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class RouteController {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private PassengersRepository passengersRepository;

    @GetMapping("/admin/flights")
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    @GetMapping("/account/{id}/flights")
    public List<Route> getUserRoutes(@PathVariable long id) {
        List<Route> routes = new ArrayList<>();
        passengersRepository.findAll().stream().filter( passenger -> passenger.getIdUser() == id )
                .forEach( passenger -> routes.add( routeRepository
                        .findById( passenger.getIdRoute() ).get() ) );
        return routes;
    }

    @PostMapping("/admin/flights")
    public Route newRoute(@RequestBody Route newRoute) {
        return routeRepository.save( newRoute );
    }

    @GetMapping("/admin/flights/{id}")
    public Route getRoute(@PathVariable long id) {
        return routeRepository.findById( id )
                .orElseThrow( () -> new RouteNotFoundException( id ) );
    }

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

    @DeleteMapping("/admin/flight/{id}")
    public void deleteFlight(@PathVariable(value = "id") long routeId)
            throws RouteNotFoundException {
        Route route = routeRepository.findById( routeId )
                .orElseThrow( () -> new RouteNotFoundException( routeId ) );
        routeRepository.delete( route );
    }
}
