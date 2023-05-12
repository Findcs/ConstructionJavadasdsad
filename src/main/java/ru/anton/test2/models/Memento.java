package ru.anton.test2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "memento")
@Getter
@Setter
public class Memento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memento_id;

    @OneToOne
    @JsonIgnore
    private Description description;

    @OneToMany(mappedBy = "memento",
                fetch = FetchType.LAZY)
    private List<DescriptionMemento> descrMementos = new ArrayList<>();

}
