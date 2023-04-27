package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.anton.test2.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u.id, u.email, u.name, u.password FROM User u WHERE u.id = :userId")
    Optional<User> findById(int userId);
    Optional<User> findByEmail(String email);
}
