package ru.anton.test2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "memento")
public class Memento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memento_id;

    @OneToOne(mappedBy = "memento_id")
    @JsonIgnore
    private Description description;

    @OneToMany(mappedBy = "id")
    private List<Descr_Memento> descrMementos;
}
