package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("RouteRepository")
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByDateAndArrivalPointAndDeparturePoint(Date date, String arrivalPoint, String departurePoint);
}
