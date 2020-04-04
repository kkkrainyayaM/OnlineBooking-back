package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.Passenger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("PassengersRepository")
public interface PassengersRepository extends CrudRepository<Passenger, Long> {
}

