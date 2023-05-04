
package ru.anton.test2.service;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;

import ru.anton.test2.repository.CompanyRepository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanys(){
        return companyRepository.findAll();
    }

    public ResponseEntity<?> add_company(String name) throws SQLException {
        Company company = new Company();
        company.setName(name);
        if (companyRepository.findByName(name).isEmpty()) {
            companyRepository.save(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
