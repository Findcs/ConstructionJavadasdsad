package ru.anton.test2.service;

import lombok.AllArgsConstructor;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)

class DescriptionServiceTest {
   @Autowired
   DescriptionService descriptionService;

   @MockBean
   DescriptionRepository descriptionRepository;



    @Test
    void add_descr() throws SQLException {
        Item item =new Item();
        item.setName("Iphone10");
        descriptionService.setItemRepository(new ItemRepository());
        descriptionService.getItemRepository().save(item);
        descriptionService.add_descr("Iphone10","price","500");
        List<Description> descriptions = descriptionService.getDescriptionRepository().findAll();
        Assertions.assertNotNull(descriptions);
        Assertions.assertEquals("price", descriptions.get(0).getName());
        Assertions.assertEquals("500", descriptions.get(0).getValue());
    }

}