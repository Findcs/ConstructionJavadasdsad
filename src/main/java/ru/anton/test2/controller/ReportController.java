package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.service.CompanyService;
import ru.anton.test2.service.ItemService;
import ru.anton.test2.service.ReportService;
import ru.anton.test2.service.ViewsService;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@RestController
@AllArgsConstructor
public class ReportController {
    CompanyService companyService;
    ReportService reportService;
    ItemService itemService;
    ViewsService viewsService;
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
    @GetMapping("/report/importcomps")
    public ResponseEntity<?> importOneComp()  {
        reportService.importXML("import.xml");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/report/views/{name}")
    public ResponseEntity<?> views(@PathVariable String name, @RequestParam int day, @RequestParam int month ,@RequestParam int year )  {
        LocalDate date = LocalDate.of(year,month,day);
        Optional<Item> item = itemService.findItemByName(name);
        if(item.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        reportService.createExcelFromDate(item.get().getViews(), date);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    //http://localhost:8080/report/views/model1?day=25&month=4&year=2023

    @GetMapping("/report/viewshigh")
    public ResponseEntity<?> allviews(@RequestParam int day, @RequestParam int month ,@RequestParam int year, @RequestParam int filtr )  {
        LocalDate date = LocalDate.of(year,month,day);
        reportService.createExcelFromDateFromHigh(viewsService.allViews(), date, filtr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
