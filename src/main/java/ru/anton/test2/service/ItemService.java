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
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.DescriptionRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
@Service
@AllArgsConstructor
public class ItemService {
    private ItemRepository itemRepository;
    private CompanyRepository companyRepository;
    public Item add_item(String name,String company_name) throws SQLException {
        Item item = new Item();
        item.setName(name);
        Optional<Company> company = companyRepository.findByName(company_name);
        if (company.isEmpty())
        {return null;}
        item.setCompany_id(company.get());
        if (itemRepository.findByName(name).isEmpty()){
            itemRepository.save(item);
            return item;}
        else return  null;
    }

    public List<Item>all_item()
    {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemByName(String name) {
        return itemRepository.findByName(name);
    }
}
