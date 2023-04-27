package ru.anton.test2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BdController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/addcomp")
    public ResponseEntity<?> add_company(@RequestParam String name) throws SQLException {
        Company company = new Company();
        company.setName(name);
        if(companyRepository.findByName(name).isEmpty()){
            companyRepository.save(company);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/all_comp")
    public List<Company> all_company()
    {
        List<Company> companys = companyRepository.findAll();
        return companys;
    }


    @GetMapping("/adduser")
    public ResponseEntity<?> add_user(@RequestParam String login,@RequestParam String password, @RequestParam String token) throws SQLException {
        User user = new User();
        user.setEmail(login);
        user.setPassword(password);
        user.setRole(0);
        if (userRepository.findByEmail(login).isEmpty()){
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);}
        else return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    //http://localhost:8080/adduser?login=asm&password=asm&token=123

    @GetMapping("/additem")
    public ResponseEntity<?> add_item(@RequestParam String name,@RequestParam String company) throws SQLException {
        Item item = new Item();
        item.setName(name);
        item.setCompany_id(companyRepository.findByName(company).get());
        if (itemRepository.findByName(name).isEmpty()){
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.OK);}
        else return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}
