package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        return  userRepository.findAll();
    }

    public User add_user(String login,String password ) throws SQLException {
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        user.setRole(1);
        if (userRepository.findByEmail(login).isEmpty()){
            userRepository.save(user);
            return user;}
        else return  null;
    }
}
