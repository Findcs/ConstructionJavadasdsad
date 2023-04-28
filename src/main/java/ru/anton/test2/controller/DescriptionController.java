package ru.anton.test2.controller;

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

import java.sql.SQLException;
import java.util.Optional;

@RestController
public class DescriptionController {

    @Autowired
    DescriptionRepository descriptionRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    DescriptionMementoRepository descriptionMementoRepository;
    @Autowired
    MementoRepository mementoRepository;
    @GetMapping("/add_descr")
    public ResponseEntity<?> add_descr(@RequestParam String item,
                                       @RequestParam String name,
                                       @RequestParam String value) throws SQLException {
        Optional<Item> itemOptional = itemRepository.findByName(item);

        if (itemOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Item currentItem = itemOptional.get();
    //Проверка на наличие описания у предмета
        Optional<Description> currentDescription =
                descriptionRepository.findByName(name);
        //Если пустое то просто добавлем новое описание
        if (currentDescription.isEmpty()) {
            Description description = new Description();
            description.setName(name);
            description.setValue(value);
            description.setItem(currentItem);
            descriptionRepository.save(description);
            return new ResponseEntity<>(description, HttpStatus.OK);
        }

        else {
            Description description = currentDescription.get();

            DescriptionMemento descrMemento = new DescriptionMemento();

            //В дескр мементо добаляю старые значения,
            // а в текущее описание записываю новые данные
            descrMemento.setName(description.getName());
            descrMemento.setValue(description.getValue());
            description.setName(name);
            description.setValue(value);


            Memento memento = description.getMemento();
            if (memento == null) {
                Memento newMemento = new Memento();
                newMemento.setDescription(description);
                mementoRepository.save(newMemento);
                memento = newMemento;
                description.setMemento(memento);
            }

            descrMemento.setMemento(memento);

            memento.getDescrMementos().add(descrMemento);

            descriptionMementoRepository.save(descrMemento);
            descriptionRepository.save(description);

            return new ResponseEntity<>(description, HttpStatus.OK);
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


}
