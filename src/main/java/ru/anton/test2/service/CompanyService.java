
package ru.anton.test2.service;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import ru.anton.test2.models.Company;

import ru.anton.test2.repository.CompanyRepository;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanys(){
        return companyRepository.findAll();
    }

    public Optional<Company> getCompanyByName(String name){return companyRepository.findByName(name);}

    public Company add_company(String name) throws SQLException {
        Company company = new Company();
        company.setName(name);
        if (companyRepository.findByName(name).isEmpty()) {
            companyRepository.save(company);
            return company;

        }
        else return null;
    }


}
