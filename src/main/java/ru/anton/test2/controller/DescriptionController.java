package ru.anton.test2.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.models.*;
import ru.anton.test2.repository.DescriptionMementoRepository;
import ru.anton.test2.repository.DescriptionRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.MementoRepository;
import ru.anton.test2.service.DescriptionService;

import java.sql.SQLException;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class DescriptionController {

    DescriptionService descriptionService;
    @GetMapping("/add_descr")
    public ResponseEntity<?> add_descr(@RequestParam String item,
                                       @RequestParam String name,
                                       @RequestParam String value) throws SQLException {
        Description description = descriptionService.add_descr(item, name, value);
        if( description != null)
            return  new ResponseEntity<>(description,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    @GetMapping("/descr_ver")
    public ResponseEntity<?> descr_ver(@RequestParam String item,
                                       @RequestParam String name,
                                       @RequestParam int version) throws SQLException {
        if (descriptionService.changeVersion(item, name, version))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





//        Optional<Description> prevDescription = descriptionRepository.findByName(name);
//        if (prevDescription.isPresent()){
//            Description prevDesc = prevDescription.get();
//            DescriptionMemento descrMemento = new DescriptionMemento();
//            descrMemento.setName(prevDesc.getName());
//            descrMemento.setValue(prevDesc.getValue());
//
//            prevDesc.getMemento().getDescrMementos().add(descrMemento);
//            descriptionRepository.save(description);
//            descriptionRepository.save(prevDesc);
//
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        else {
//            descriptionRepository.save(description);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
    }
    //http://localhost:8080/add_descr?item=model1&name=price&value=500


