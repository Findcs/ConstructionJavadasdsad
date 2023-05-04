package ru.anton.test2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String name;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "company_id")
    private Company company_id;

    @OneToMany(mappedBy = "item")
    private List<Description> descriptions;

    @OneToMany(mappedBy = "item")
    private List<Views> views;

    public Item() {
    }
    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id=" + item_id +
                ", descriptions=" + descriptions +
                '}';
    }
}
