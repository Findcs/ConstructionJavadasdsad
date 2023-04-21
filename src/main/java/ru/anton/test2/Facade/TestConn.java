package ru.anton.test2.Facade;

import java.sql.*;

public class TestConn {
    static String USER_NAME = "root";
    static String PASSWORD = "roots";
    static String URL = "jdbc:mysql://localhost:3306/mysql";

    static Statement statement;
    static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try{
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testcon() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection con=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            if (con != null) { System.out.println("Successfully connected to MySQL database test"); }




        }catch(Exception e){ System.out.println(e);}

    }

}
