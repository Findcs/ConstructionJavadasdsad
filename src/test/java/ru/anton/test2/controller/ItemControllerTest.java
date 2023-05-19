package ru.anton.test2.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.Views;
import ru.anton.test2.repository.*;
import ru.anton.test2.service.DescriptionService;
import ru.anton.test2.service.ItemService;
import ru.anton.test2.service.ViewsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemControllerTest {
    ItemController itemController;
    ItemService itemService;
    ViewsService viewsService;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ViewsRepository viewsRepository;

    @BeforeEach
    public void setUp(){
        viewsService = new ViewsService(itemRepository, viewsRepository);
        itemService = new ItemService(itemRepository,companyRepository);
        itemController= new ItemController(itemService,viewsService);

    }
    @BeforeEach
    public void clearDatabase() {
        companyRepository.deleteAll();
        itemRepository.deleteAll();
    }

    @Test
    void all_item() {
        Company company = new Company();
        company.setName("Xiaomi");
        companyRepository.save(company);
        for (int i = 1; i < 10; i++) {
            Item item1 = new Item();
            item1.setName("item" + i);
            item1.setCompany_id(company);
            itemRepository.save(item1);
        }
        Company company1 = new Company();
        company1.setName("Apple");
        companyRepository.save(company1);
        for (int i = 1; i < 10; i++) {
            Item item1 = new Item();
            item1.setName("item" + i);
            item1.setCompany_id(company1);
            itemRepository.save(item1);
        }
        ResponseEntity<List<Item>> itemsByName = itemController.all_item("company");
        List<Item> items = itemsByName.getBody();
        for(int i=0; i< 9; i++)
        {
            Assertions.assertEquals("Apple",items.get(i).getCompany_id().getName());
        }
    }
}