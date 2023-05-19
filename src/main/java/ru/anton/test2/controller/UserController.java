
package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.models.User;
import ru.anton.test2.service.UserService;

import java.sql.SQLException;

@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/adduser")
    public ResponseEntity<?> add_user(@RequestParam String login,@RequestParam String password) throws SQLException {
        User user = userService.add_user(login,password);
        if (user != null)
            return new ResponseEntity<>(user,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    //http://localhost:8080/adduser?login=asm&password=asm&token=123

    @GetMapping("/all_users")
    public ResponseEntity all_users()
    {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}

