package ru.anton.test2.models;

import jakarta.persistence.*;

@Entity
@Table
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name_descr;
    private String descr_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName_descr() {
        return name_descr;
    }

    public void setName_descr(String name_descr) {
        this.name_descr = name_descr;
    }

    public String getDescr_value() {
        return descr_value;
    }

    public void setDescr_value(String descr_value) {
        this.descr_value = descr_value;
    }
}
