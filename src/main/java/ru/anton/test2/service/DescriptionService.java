
package ru.anton.test2.service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.*;
import ru.anton.test2.repository.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
@Service
@AllArgsConstructor
@Getter
@Setter
public class DescriptionService {
    private DescriptionRepository descriptionRepository;

    private DescriptionMementoRepository descriptionMementoRepository;
    private ItemRepository itemRepository;
    private MementoRepository mementoRepository;

    public Description add_descr(String item, String name, String value) throws SQLException {
        Optional<Item> itemOptional = itemRepository.findByName(item);

        if (itemOptional.isEmpty()) {
            return null;
        }
        Item currentItem = itemOptional.get();
        //Проверка на наличие описания у предмета
        Optional<Description> currentDescription =
                descriptionRepository.findByNameAndItemId(name,currentItem.getItem_id());
        //Если пустое то просто добавлем новое описание
        if (currentDescription.isEmpty()) {
            Description description = new Description();
            description.setName(name);
            description.setValue(value);
            description.setItem(currentItem);
            descriptionRepository.save(description);
            return description;
        } else {
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

            return description;
        }
    }
    public boolean changeVersion( String item, String name, int id) throws SQLException {
        Optional<Item> itemOptional = itemRepository.findByName(item);

        if (itemOptional.isEmpty()) {
            return false;
        }
        Item currentItem = itemOptional.get();
        Optional<Description> currentDescription = descriptionRepository.findByName(name);
        if (currentDescription.isEmpty()) {
            return false;
        }
        Description olddescr = currentDescription.get();
        add_descr(olddescr.getItem().getName(), olddescr.getName(),olddescr.getValue());
        Optional<DescriptionMemento> descriptionMemento = descriptionMementoRepository.findById(id);
        if(descriptionMemento.isEmpty()){
            return false;
        }
        olddescr.setName(descriptionMemento.get().getName());
        olddescr.setValue(descriptionMemento.get().getValue());
        descriptionRepository.save(olddescr);
        return true;




    }

}
