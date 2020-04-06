package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.Route;
import by.project.onlinebooking.exceptions.RouteNotFoundException;

import java.util.List;

public interface RouteService {
    /**
     * Add a new route
     *
     * @param route - new Route
     * @return added route
     */
    Route add(Route route);

    /**
     * Get a route by ID
     *
     * @param id - route ID
     * @return route
     * @throws RouteNotFoundException if route doesn't exist
     */
    Route getById(long id) throws RouteNotFoundException;

    /**
     * Get list of all routes
     *
     * @return a list of all routes
     */
    List<Route> getAll();

    /**
     * Update route
     *
     * @param route - existed route with updated fields
     * @return updated route
     * @throws RouteNotFoundException if route doesn't exist
     */
    Route update(Route route) throws RouteNotFoundException;

    /**
     * Delete route by ID
     *
     * @param id - route ID
     * @throws RouteNotFoundException if route doesn't exist
     */
    void delete(long id) throws RouteNotFoundException;
}
