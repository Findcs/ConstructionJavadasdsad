package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.models.Views;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface ViewsRepository extends JpaRepository<Views, Integer> {
    Optional<Views> findByDate(LocalDate date);

}
