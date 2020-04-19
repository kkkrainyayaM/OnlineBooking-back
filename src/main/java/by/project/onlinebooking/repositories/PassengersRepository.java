package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PassengersRepository")
public interface PassengersRepository extends JpaRepository<Passenger, Long> {

    void deleteByUserId(long id);

    void deleteByRouteId(long id);

    List<Passenger> getAllByRouteId(long id);

    List<Passenger> getAllByUserId(long id);
}
