package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;

import java.util.Optional;
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Optional<Company> findById(int item_id);
    Optional<Company> findByName(String name);
}
