package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SortByComp;
import ru.anton.test2.facade.SortByName;
import ru.anton.test2.facade.SortParam;
import ru.anton.test2.facade.Sorter;
import ru.anton.test2.models.Item;
import ru.anton.test2.service.ItemService;
import ru.anton.test2.service.ViewsService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ItemController {

    private ItemService itemService;
    private ViewsService viewsService;
    @GetMapping("/additem")
    public ResponseEntity<?> add_item(@RequestParam String name,@RequestParam String company) throws SQLException {
        Item item = itemService.add_item(name,company);
        if (item !=null)
            return new ResponseEntity<>(item, HttpStatus.OK);
        else
            return  new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    //http://localhost:8080/additem?name=model2&company=Xiaomi

    @GetMapping("/all_item")
    public ResponseEntity<List<Item>> all_item(@RequestParam(value = "filter", required = false) String filter)
    {
        SortParam sortParam = null;
        if (filter == null) {
            // Если фильтр не указан, возвращаем список продуктов без сортировки
            return new ResponseEntity<>(itemService.all_item(), HttpStatus.OK);
        } else if (filter.equals("name")) {
            sortParam = new SortByName();
        } else if (filter.equals("company")) {
            sortParam = new SortByComp();
        }  else {
            // Если указанный фильтр не соответствует доступным вариантам, возвращаем ошибку
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        // Создание объекта сортировщика продуктов с переданной стратегией сортировки
        Sorter sorter = new Sorter(sortParam);

        // Сортировка списка продуктов с помощью сортировщика
        List<Item> items = sorter.sort(itemService.all_item());

        return new ResponseEntity<>(items,HttpStatus.OK);
    }

    @GetMapping("/item/{name}")
    public ResponseEntity<?> find_item(@PathVariable String name)
    {
        Optional<Item> item = itemService.findItemByName(name);
        if (item.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        viewsService.add_view(name);
        return new ResponseEntity<>(item.get(),HttpStatus.OK);
    }

}
