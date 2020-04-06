package by.project.onlinebooking.services;

import by.project.onlinebooking.dto.PassengerDto;

import java.util.List;

public interface PassengerService {

    /**
     * Add a new passenger
     *
     * @param passenger - Passenger
     * @return new passenger
     */
    PassengerDto add(PassengerDto passenger);

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
    List<PassengerDto> getAllByUserId(long id);

    /**
     * Get all passengers of route
     *
     * @param id - route id
     * @return list of passengers
     */
    List<PassengerDto> getAllByRouteId(long id);

    /**
     * Update passenger
     *
     * @param passenger - Passenger
     * @return updated passenger
     */
    PassengerDto update(PassengerDto passenger);
}
