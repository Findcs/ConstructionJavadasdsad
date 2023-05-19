package ru.anton.test2.facade;

import org.springframework.stereotype.Component;
import ru.anton.test2.models.Item;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Component
public class SortByComp implements  SortParam{
    @Override
    public List<Item> sort(List<Item> items) {
        Comparator<Item> companyComparator = Comparator.comparing(
                item -> item.getCompany_id().getName());
        Collections.sort(items, companyComparator);
        return items;
    }
}
