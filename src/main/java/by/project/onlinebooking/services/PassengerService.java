package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.Passenger;

import java.util.List;

public interface PassengerService {

    /**
     * Add a new passenger
     *
     * @param passenger - Passenger
     * @return new passenger
     */
    Passenger add(Passenger passenger);

    /**
     * Delete passenger by user ID
     *
     * @param id - passenger(user) id
     */
    void deleteByUserId(long id);

    /**
     * Delete passenger by route ID
     *
     * @param id - route id
     */
    void deleteByRouteId(long id);

    /**
     * Get all routes of passenger
     *
     * @param id - passenger id
     * @return list of passengers
     */
    List<Passenger> getAllByUserId(long id);

    /**
     * Get all passengers of route
     *
     * @param id - route id
     * @return list of passengers
     */
    List<Passenger> getAllByRouteId(long id);

    /**
     * Update passenger
     *
     * @param passenger - Passenger
     * @return updated passenger
     */
    Passenger update(Passenger passenger);
}
