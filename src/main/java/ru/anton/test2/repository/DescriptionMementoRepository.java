package ru.anton.test2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anton.test2.models.DescriptionMemento;

public interface DescriptionMementoRepository extends JpaRepository<DescriptionMemento, Integer> {
}
