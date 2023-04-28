package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.test2.models.Memento;

public interface MementoRepository extends JpaRepository<Memento, Integer> {

}
