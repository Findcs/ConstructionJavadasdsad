package ru.anton.test2.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String name;


    @ManyToOne()
    @JoinColumn(name = "company_id")
    private Company company_id;
    private List<Description> descriptions;


    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return item_id;
    }

    public void setId(int id) {
        this.item_id = id;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
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
