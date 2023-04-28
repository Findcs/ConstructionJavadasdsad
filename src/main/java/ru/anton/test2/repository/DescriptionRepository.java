package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.anton.test2.models.Description;


import java.util.Optional;

public interface DescriptionRepository extends JpaRepository<Description, Integer> {
    Optional<Description> findById(int id);
    Optional<Description> findByName(String name);
    @Query("SELECT d FROM Description d WHERE d.name = :name AND d.item.item_id = :itemId")
    Optional<Description> findByNameAndItem(String name, int itemId);
}
