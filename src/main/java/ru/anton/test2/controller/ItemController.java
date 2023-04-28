package ru.anton.test2.controller;

import jakarta.transaction.Transactional;
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
public class ItemController {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ItemRepository itemRepository;
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

    @GetMapping("/all_item")
    public ResponseEntity<?> all_item()
    {
        List<Item> items = itemRepository.findAll();
        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/find_item")
    public ResponseEntity<?> find_item(@RequestParam String name)
    {
        Item item = itemRepository.findByName(name).get();
        return new ResponseEntity<>(item,HttpStatus.OK);
    }
    //http://localhost:8080/find_item?name=model1
}
