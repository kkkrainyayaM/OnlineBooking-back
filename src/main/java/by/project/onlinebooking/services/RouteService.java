package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.Route;

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
     */
    Route getById(long id);

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
     */
    Route update(Route route);

    /**
     * Delete route by ID
     *
     * @param id - route ID
     */
    void delete(long id);
}
