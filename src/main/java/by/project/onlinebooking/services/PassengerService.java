package by.project.onlinebooking.services;

import by.project.onlinebooking.DTO.PassengerDTO;

import java.util.List;

public interface PassengerService {

    /**
     * Add a new passenger
     *
     * @param passenger - Passenger
     * @return new passenger
     */
    PassengerDTO add(PassengerDTO passenger);

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
    List<PassengerDTO> getAllByUserId(long id);

    /**
     * Get all passengers of route
     *
     * @param id - route id
     * @return list of passengers
     */
    List<PassengerDTO> getAllByRouteId(long id);

    /**
     * Update passenger
     *
     * @param passenger - Passenger
     * @return updated passenger
     */
    PassengerDTO update(PassengerDTO passenger);
}
