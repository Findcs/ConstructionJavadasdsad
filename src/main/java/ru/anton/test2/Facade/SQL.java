package ru.anton.test2.Facade;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQL implements DB {
    private static String USER_NAME = "root";
    private static String PASSWORD = "roots";
    private static String URL = "jdbc:mysql://localhost:3306/mysql";

    static Statement statement;
    static Connection connection;
    public SQL() {
    }

    public static void openConnection()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            if (connection != null) { System.out.println("Successfully connected to MySQL database test"); }
            statement = connection.createStatement();
            boolean resultSet= statement.execute("INSERT INTO constr.companys (name) VALUES('Samsung')");
            System.out.println(resultSet);

        }
        catch(Exception e){ System.out.println(e);}
    }


    public User search (String username){
        // Заглушка
        throw new UnsupportedOperationException();
    }
}
