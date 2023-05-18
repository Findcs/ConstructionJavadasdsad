package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.anton.test2.models.Description;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.Views;
import ru.anton.test2.repository.*;

import javax.swing.text.View;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReportServiceTest {
    ReportService reportService;


    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    DescriptionMementoRepository descriptionMementoRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    MementoRepository mementoRepository;
    @Autowired
    ViewsRepository viewsRepository;
    @Autowired
    CompanyRepository companyRepository;
    @BeforeEach
    public void setUp(){
        reportService  = new ReportService(companyRepository,itemRepository, new ItemService(itemRepository,companyRepository));;

    }
    @BeforeEach
    public void clearDatabase() {
        descriptionMementoRepository.deleteAll();
        mementoRepository.deleteAll();
        descriptionRepository.deleteAll();
        viewsRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    void createExcelFromDateFromHigh() {
        LocalDate date = LocalDate.of(2023,04,1);
        Random random = new Random();
        for (int j = 5 ; j < 8 ; j ++) {
            Item item1 = new Item();
            item1.setName("Iphone"+ j);
            itemRepository.save(item1);
            for (int i = 0; i < 10; i++) {

                Views views = new Views();
                views.setItem(item1);
                views.setDate(LocalDate.of(2023, 05, i + 1));
                views.setViews(random.nextInt(1000));
                viewsRepository.save(views);
            }
        }

        List<Views> viewsList = viewsRepository.findAll();
        Assertions.assertTrue(reportService.createExcelFromDateFromHigh(viewsList,date, 500));

    }
}