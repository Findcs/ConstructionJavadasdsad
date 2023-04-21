package ru.anton.test2.Facade;

import java.sql.Connection;
import java.sql.Driver;

public class SQL implements DB {
    public SQL(String file) {
    }


    public User search (String username){
        // Заглушка
        throw new UnsupportedOperationException();
    }
}
