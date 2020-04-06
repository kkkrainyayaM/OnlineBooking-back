package by.project.onlinebooking.services;

import by.project.onlinebooking.DTO.RouteDTO;

import java.util.List;

public interface RouteService {
    /**
     * Add a new route
     *
     * @param route - new Route
     * @return added route
     */
    RouteDTO add(RouteDTO route);

    /**
     * Get a route by ID
     *
     * @param id - route ID
     * @return route
     */
    RouteDTO getById(long id);

    /**
     * Get list of all routes
     *
     * @return a list of all routes
     */
    List<RouteDTO> getAll();

    /**
     * Update route
     *
     * @param route - existed route with updated fields
     * @return updated route
     */
    RouteDTO update(RouteDTO route);

    /**
     * Delete route by ID
     *
     * @param id - route ID
     */
    void delete(long id);
}
