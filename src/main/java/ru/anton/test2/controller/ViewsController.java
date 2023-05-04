package ru.anton.test2.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;
import ru.anton.test2.service.ItemService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor

public class ViewsController {
    private ItemService itemService;
    @GetMapping("/item/{name}/views")
    public ResponseEntity<?> find_item(@PathVariable String name)
    {
        Optional<Item> item = itemService.findItemByName(name);
        if (item.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item.get().getViews(),HttpStatus.OK);
    }
}