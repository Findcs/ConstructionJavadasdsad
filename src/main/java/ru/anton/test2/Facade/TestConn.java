package ru.anton.test2.Facade;

import java.sql.*;

public class TestConn {
    static String USER_NAME = "root";
    static String PASSWORD = "roots";
    static String URL = "jdbc:mysql://localhost:3306/mysql";

    static Statement statement;
    static Connection connection;


    public static void testcon() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
            if (!con.isClosed()) { System.out.println("Successfully connected to MySQL database test"); }
            con.close();
            if (con.isClosed()) { System.out.println("Successfully not connected to MySQL database test"); }





        }catch(Exception e){ System.out.println(e);}

    }

}
