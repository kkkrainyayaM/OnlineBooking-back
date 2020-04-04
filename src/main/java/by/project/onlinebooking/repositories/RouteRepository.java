package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("RouteRepository")
public interface RouteRepository extends JpaRepository<Route, Long> {
}
