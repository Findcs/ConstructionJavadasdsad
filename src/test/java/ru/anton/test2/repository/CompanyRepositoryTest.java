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
class CompanyRepositoryTest {
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
    void findByName() {
        Company company = new Company();
        company.setName("Xiaomi");
        companyRepository.save(company);
        // поиск в бд
        Company selectedCompany = companyRepository.findByName("Xiaomi").get();
        Assertions.assertEquals(company,selectedCompany);
        Assertions.assertEquals(company.getName(),selectedCompany.getName());
    }

    @Test
    void insertTest() {
        //        given
        Company company = new Company();
        company.setName("Apple");
        companyRepository.save(company);
        List<Company> companies = companyRepository.findAll();
        Assertions.assertNotNull(companies);
        Assertions.assertEquals(company.getName(), companies.get(0).getName());
    }

    @Test
    void deleteTest() {
        //        given
        Company company1 = new Company();
        company1.setName("Apple");
        companyRepository.save(company1);
        Company company2 = new Company();
        company2.setName("Xiaomi");
        companyRepository.save(company2);
        //        when
        companyRepository.delete(company1);
        List<Company> companies = companyRepository.findAll();
        //        then
        Assertions.assertNotNull(companies);
        Assertions.assertEquals(company2.getName(), companies.get(0).getName());
        Assertions.assertEquals(1, companies.size());
    }
    @Test
    void updateTest() {
        //        given
        Company company =new Company();
        company.setName("Apple");
        companyRepository.save(company);
        //        when
        List<Company> companies = companyRepository.findAll();
        companies.get(0).setName("Xiaomi");
        //        then
        Assertions.assertNotNull(companies);
        Assertions.assertEquals(company.getName(), companies.get(0).getName());
    }
}