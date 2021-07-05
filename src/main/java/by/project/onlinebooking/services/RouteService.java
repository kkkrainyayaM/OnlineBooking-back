package by.project.onlinebooking.services;

import by.project.onlinebooking.dto.RouteDto;
import by.project.onlinebooking.dto.SearchDto;

import java.util.List;

public interface RouteService {
    /**
     * Add a new route
     *
     * @param route - new Route
     * @return added route
     */
    RouteDto add(RouteDto route);

    /**
     * Get a route by ID
     *
     * @param id - route ID
     * @return route
     */
    RouteDto getById(long id);

    /**
     * Get list of all routes
     *
     * @return a list of all routes
     */
    List<RouteDto> getAll();

    /**
     * Update route
     *
     * @param route - existed route with updated fields
     * @return updated route
     */
    RouteDto update(RouteDto route);

    /**
     * Delete route by ID
     *
     * @param id - route ID
     */
    void delete(long id);

    /**
     * Get a list of results of search
     *
     * @param search - search
     * @return list of routes
     */
    List<RouteDto> getBySearch(SearchDto search);
}
