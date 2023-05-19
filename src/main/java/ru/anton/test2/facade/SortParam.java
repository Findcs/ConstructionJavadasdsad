package ru.anton.test2.facade;

import org.springframework.stereotype.Component;
import ru.anton.test2.models.Item;

import java.util.List;
@Component
public interface SortParam {
    List<Item> sort(List<Item> items);
}
