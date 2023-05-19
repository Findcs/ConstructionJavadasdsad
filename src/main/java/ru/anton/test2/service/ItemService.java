package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.ItemRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
