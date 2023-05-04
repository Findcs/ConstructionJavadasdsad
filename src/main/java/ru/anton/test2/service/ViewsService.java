package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.models.Views;
import ru.anton.test2.repository.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.View;

@Service
@AllArgsConstructor

public class ViewsService {
    private ItemRepository itemRepository;

    private ViewsRepository viewsRepository;

    public void add_view(String name){
        Item item = itemRepository.findByName(name).get();
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        Optional<Views> views = viewsRepository.findByDate(formattedDate);
        if (views.isEmpty())
        {
            Views views_save = new Views();
            views_save.setItem(item);
            views_save.setDate(formattedDate);
            views_save.setViews(1);
            viewsRepository.save(views_save);
            return;
        }
        else{
            Views view_save = views.get();
            view_save.setViews(view_save.getViews()+1);
            viewsRepository.save(view_save);
        }


    }
}
