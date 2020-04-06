package by.project.onlinebooking.services;

import by.project.onlinebooking.entities.Passenger;
import by.project.onlinebooking.exceptions.PassengerNotFoundException;

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
     * @throws PassengerNotFoundException if passenger doesn't exist
     */
    void deleteByUserId(long id) throws PassengerNotFoundException;

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
     * @throws PassengerNotFoundException if passenger doesn't exist
     */
    Passenger update(Passenger passenger) throws PassengerNotFoundException;
}
