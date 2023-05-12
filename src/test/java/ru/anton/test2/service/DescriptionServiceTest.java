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
import ru.anton.test2.repository.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DescriptionServiceTest {
   DescriptionService descriptionService;
   ItemService itemService;

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
        descriptionService = new DescriptionService(descriptionRepository,
                descriptionMementoRepository,
                itemRepository,
                mementoRepository);
        itemService = new ItemService(itemRepository,
                companyRepository);

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
    void add_descr() throws SQLException {
        Item item =new Item();
        item.setName("Iphone10");
        // descriptionService.setItemRepository(new ItemRepository());
        // descriptionService.getItemRepository().save(item);
        itemRepository.save(item);
        descriptionService.add_descr("Iphone10","price","500");

        List<Description> descriptions = descriptionRepository.findAll();

        Assertions.assertEquals(1, descriptions.size());
        Assertions.assertEquals("price", descriptions.get(0).getName());
        Assertions.assertEquals("500", descriptions.get(0).getValue());
    }

    @Test
    void add_2descr() throws SQLException {
        //itemService.add_item("Iphone10", "Apple");

        Item item =new Item();
        item.setName("Iphone10");
        // descriptionService.setItemRepository(new ItemRepository());
        // descriptionService.getItemRepository().save(item);
        itemRepository.save(item);
        descriptionService.add_descr(item.getName(), "price","500");
        //descriptionService.add_descr(item.getName(),"price","600");


        List<Description> descriptions = descriptionRepository.findAll();

        Item selectedItem = itemRepository.findById(item.getItem_id()).get();


        Assertions.assertNotEquals(item, selectedItem);
        Assertions.assertEquals(1, descriptions.size());
        Assertions.assertEquals("price", descriptions.get(0).getName());
        Assertions.assertEquals("600", descriptions.get(0).getValue());

        Assertions.assertNotNull(selectedItem.getDescriptions());

        //Assertions.assertEquals("500",item.getDescriptions().get(0).getValue());
    }


}