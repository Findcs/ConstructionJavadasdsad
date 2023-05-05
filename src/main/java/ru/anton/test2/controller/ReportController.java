package ru.anton.test2.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;
import ru.anton.test2.service.CompanyService;
import ru.anton.test2.service.ItemService;
import ru.anton.test2.service.ReportService;
import ru.anton.test2.service.ViewsService;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RestController
@AllArgsConstructor
public class ReportController {
    CompanyService companyService;
    ReportService reportService;
    @GetMapping("/report/comp/{name}")
    public ResponseEntity<?> report(@PathVariable String name)  {
        Optional<Company> company = companyService.getCompanyByName(name);
        if(company.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reportService.writeToXML_comp(company.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/report/comps")
    public ResponseEntity<?> report_all_comps()  {
        List <Company> companys = companyService.getAllCompanys();
        if(companys.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reportService.writeToXML_all_comp(companys);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
