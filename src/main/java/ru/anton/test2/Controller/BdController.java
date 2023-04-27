package ru.anton.test2.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.Facade.SQL;
import ru.anton.test2.Facade.User;
import ru.anton.test2.Facade.TestConn;
import ru.anton.test2.Models.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class BdController {
    @GetMapping("/addcomp")
    public ResponseEntity<?> add_company(@RequestParam String name) throws SQLException {

        SQL.statement.execute("SELECT * FROM constr.companys WHERE name ='"+ name+"'");
        ResultSet resultSet = SQL.statement.getResultSet();
        if (resultSet.next()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        String query = "INSERT INTO constr.companys (name) VALUES('"+name+"')";
        SQL.statement.execute(query);
        SQL.connection.commit();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all_comp")
    public ArrayList<Company> all_company()
    {

        ArrayList<Company> companys= new ArrayList<>();
        try {
           SQL.statement.execute("SELECT * FROM constr.companys");
           ResultSet resultSet = SQL.statement.getResultSet();
           while (resultSet.next())
           {
               companys.add(new Company(resultSet.getInt(1), resultSet.getString(2)));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return companys;
    }

    @GetMapping("/adduser")
    public ResponseEntity<?> add_user(@RequestParam String login,@RequestParam String password, @RequestParam String token) throws SQLException {
        SQL.statement.execute("SELECT * FROM constr.companys WHERE user_login ='"+ login+"'");
        ResultSet resultSet = SQL.statement.getResultSet();
        if (resultSet.next()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        String query = "INSERT INTO constr.companys (name) VALUES('"+name+"')";
        SQL.statement.execute(query);
        SQL.connection.commit();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
