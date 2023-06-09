package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.Views;
import ru.anton.test2.repository.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ViewsService {
    private ItemRepository itemRepository;

    private ViewsRepository viewsRepository;
    public List<Views> allViews(){
        return viewsRepository.findAll();
    }
    public void add_view(String name){
        Item item = itemRepository.findByName(name).get();
        LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        LocalDate date = LocalDate.of(year, month, day);
        Optional<Views> views = viewsRepository.findByDateAndItem(date,item);
        if (views.isEmpty())
        {
            Views views_save = new Views();
            views_save.setItem(item);
            views_save.setDate(date);
            views_save.setViews(1);
            viewsRepository.save(views_save);
        }
        else{
            Views view_save = views.get();
            view_save.setViews(view_save.getViews()+1);
            viewsRepository.save(view_save);
        }


    }
}
