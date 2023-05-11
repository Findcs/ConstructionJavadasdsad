package ru.anton.test2.repository;

import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Description;
import ru.anton.test2.models.Item;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    MementoRepository mementoRepository;

    @Autowired
    DescriptionMementoRepository descriptionMementoRepository;

    @Autowired
    ViewsRepository viewsRepository;




    @BeforeEach
    public void clearDatabase() {
        descriptionMementoRepository.deleteAll();
        mementoRepository.deleteAll();
        descriptionRepository.deleteAll();
        viewsRepository.deleteAll();
        itemRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void findById() {
        Company company = new Company();
        company.setName("Apple");
        companyRepository.save(company);
        Item item = new Item();
        item.setName("Iphone11");
        item.setCompany_id(company);
        itemRepository.save(item);
        Item selectedItem = itemRepository.findById(item.getItem_id()).get();
        Assertions.assertNotNull(selectedItem);
        Assertions.assertEquals("Iphone11", selectedItem.getName());
        Assertions.assertEquals(company,selectedItem.getCompany_id());
    }
    @Test
    void insertTest() {
        //        given
        Company company = new Company();
        company.setName("Apple");
        companyRepository.save(company);
        Item item = new Item();
        item.setName("Iphone12");
        item.setCompany_id(company);
        itemRepository.save(item);
        //        when
        List<Item> items = itemRepository.findAll();
        //        then
        Assertions.assertNotNull(items);
        Assertions.assertEquals(item.getName(), items.get(0).getName());
    }

    @Test
    void deleteTest() {
        //        given
        Item item1 = new Item();
        item1.setName("Ipad1");
        itemRepository.save(item1);
        Item item2 = new Item();
        item2.setName("Ipad3");
        itemRepository.save(item2);
        //        when
        itemRepository.delete(item1);
        List<Item> items = itemRepository.findAll();
        //        then
        Assertions.assertNotNull(items);
        Assertions.assertEquals(item2.getName(), items.get(0).getName());
        Assertions.assertEquals(1, items.size());
    }

    @Test
    void updateTest() {
        //        given
        Item item =new Item();
        item.setName("Iphone10");
        itemRepository.save(item);
        //        when
        List<Item> items = itemRepository.findAll();
        items.get(0).setName("Iphone20");
        //        then
        Assertions.assertNotNull(items);
        Assertions.assertEquals(item.getName(), items.get(0).getName());
    }

}