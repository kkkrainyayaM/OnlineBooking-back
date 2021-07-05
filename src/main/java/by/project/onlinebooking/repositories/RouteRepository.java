package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("RouteRepository")
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findAllByDateAndArrivalPointAndDeparturePoint(LocalDate date, String arrivalPoint, String departurePoint);
}
