package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.services.RouteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @ApiOperation(value = "Get a list of all flights")
    @GetMapping("/flights")
    public List<RouteDto> getAll() {
        return routeService.getAll();
    }

    @ApiOperation(value = "Get a flight by ID")
    @GetMapping("/flights/{id}")
    public RouteDto getRoute(@PathVariable long id) {
        return routeService.getById( id );
    }

    @ApiOperation(value = "Add a flight")
    @PostMapping("/flights")
    public RouteDto addRoute(@RequestBody RouteDto route) {
        return routeService.add( route );
    }

    @ApiOperation(value = "Update a flight")
    @PutMapping("/flights")
    public RouteDto updateRoute(@Valid @RequestBody RouteDto route) {
        return routeService.update( route );
    }

    @ApiOperation(value = "Delete a flight")
    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable(value = "id") long id) {
        routeService.delete( id );
    }
}
