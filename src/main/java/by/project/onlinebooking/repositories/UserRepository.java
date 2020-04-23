package by.project.onlinebooking.repositories;

import by.project.onlinebooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhoneAndPassword(String phone, String password);

    User findByPhone(String phone);
}
