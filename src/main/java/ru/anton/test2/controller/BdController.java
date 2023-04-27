package ru.anton.test2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class BdController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/addcomp")
    public ResponseEntity<?> add_company(@RequestParam String name) throws SQLException {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all_comp")
    public ResponseEntity<?> all_company()
    {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/adduser")
    public ResponseEntity<?> add_user(@RequestParam String login,@RequestParam String password, @RequestParam String token) throws SQLException {
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        if (userRepository.findByEmail(login).isEmpty()){
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);}
        else return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
