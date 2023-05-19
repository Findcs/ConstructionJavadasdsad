package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.models.Item;
import ru.anton.test2.service.ItemService;

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
