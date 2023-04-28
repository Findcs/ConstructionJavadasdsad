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
import ru.anton.test2.models.Description;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.DescriptionRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    ItemRepository itemRepository;
    @GetMapping("/add_descr")
    public ResponseEntity<?> add_descr(@RequestParam String item,@RequestParam String name,@RequestParam String value) throws SQLException {
        Description description = new Description();
        description.setName(name);
        description.setValue(value);
        description.setItem(itemRepository.findByName(item).get());
        if (descriptionRepository.findByName(name).isEmpty()){
            descriptionRepository.save(description);
            return new ResponseEntity<>(HttpStatus.OK);}
        else return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    //http://localhost:8080/add_descr?item=model1&name=price&value=500


}
