package by.project.onlinebooking.controllers;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.dto.SearchDto;
import by.project.onlinebooking.services.RouteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @ApiOperation(value = "Get a list of all routes")
    @GetMapping("/routes")
    public List<RouteDto> getAll() {
        return routeService.getAll();
    }

    @ApiOperation(value = "Get a route by ID")
    @GetMapping("/routes/{id}")
    public RouteDto getRoute(@PathVariable long id) {
        return routeService.getById(id);
    }

    @ApiOperation(value = "Add a route")
    @PostMapping("/routes")
    public RouteDto addRoute(@Valid @RequestBody RouteDto route) {
        return routeService.add(route);
    }

    @ApiOperation(value = "Update a route")
    @PutMapping("/routes")
    public RouteDto updateRoute(@Valid @RequestBody RouteDto route) {
        return routeService.update(route);
    }

    @ApiOperation(value = "Delete a route")
    @DeleteMapping("/routes/{id}")
    public void deleteRoute(@PathVariable(value = "id") long id) {
        routeService.delete(id);
    }

    @ApiOperation(value = "Get a list of route by search")
    @PostMapping("/routes/search")
    public List<RouteDto> getBySearch(@Valid @RequestBody SearchDto search) {
        return routeService.getBySearch(search);
    }
}
