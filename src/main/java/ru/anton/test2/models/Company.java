package ru.anton.test2.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companys")
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;

    private String name;


    @OneToMany(mappedBy = "company_id")
    private List<Item> items;

    public Company(int id,String name) {
        company_id = id;
        this.name = name;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Company() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    @Override
    public String toString() {
        return "Company{" +
                "company_id=" + company_id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
