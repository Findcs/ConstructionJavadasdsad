package ru.anton.test2.Models;

public class Company {
    private int company_id;

    private String name;

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
}
