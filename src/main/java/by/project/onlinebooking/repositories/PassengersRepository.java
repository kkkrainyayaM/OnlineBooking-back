package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("PassengersRepository")
public interface PassengersRepository extends JpaRepository<Passenger, Long> {
}

