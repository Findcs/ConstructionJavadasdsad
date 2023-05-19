
package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.service.CompanyService;



@RestController
@AllArgsConstructor
public class CompanyController {
    private CompanyService companyService;


    @GetMapping("/addcomp")
    public ResponseEntity<?> add_company(@RequestParam String name) throws SQLException {
        if(companyService.add_company(name)==null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/all_comp")
    public ResponseEntity<?> all_company()
    {
        //List<Company> companys = companyRepository.findAll();
        return new ResponseEntity<>(companyService.getAllCompanys(),HttpStatus.OK);
    }
}