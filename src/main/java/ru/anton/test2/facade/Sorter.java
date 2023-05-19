package ru.anton.test2.facade;

import lombok.AllArgsConstructor;
import ru.anton.test2.models.Item;

import java.util.List;

@AllArgsConstructor
public class Sorter {
    private SortParam sortParam;

    public List<Item> sort(List<Item> items)
    {
        return  sortParam.sort(items);
    }
}
