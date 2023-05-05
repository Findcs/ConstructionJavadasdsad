package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.DescriptionRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

@Service
@AllArgsConstructor
public class ReportService {

    private CompanyRepository companyRepository;
    private ItemRepository itemRepository;

    public void writeToXML_comp(Company company){
        try{
        // Создание объекта DocumentBuilderFactory и DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Создание нового документа XML
        Document document = builder.newDocument();

        // Создание корневого элемента и добавление его в документ
        Element rootElement = document.createElement("company");
        document.appendChild(rootElement);
        Element compIdElement = document.createElement("id");
        compIdElement.appendChild(document.createTextNode(String.valueOf(company.getCompany_id())));
        rootElement.appendChild(compIdElement);

        // Добавление элементов для полей объекта Company
        Element nameElement = document.createElement("name");
        nameElement.appendChild(document.createTextNode(company.getName()));
        rootElement.appendChild(nameElement);
        List<Item> itemList = company.getItems();
            for (Item item : itemList) {
                Element itemElement = document.createElement("item");
                rootElement.appendChild(itemElement);

                Element idElement = document.createElement("id");
                idElement.appendChild(document.createTextNode(String.valueOf(item.getItem_id())));
                itemElement.appendChild(idElement);

                Element nameItemElement = document.createElement("nameItem");
                nameItemElement.appendChild(document.createTextNode(item.getName()));
                itemElement.appendChild(nameItemElement);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("report.xml"));
            transformer.transform(source, result);

            System.out.println("XML-файл успешно создан!");
        }
        catch (ParserConfigurationException e) {
        e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeToXML_all_comp(List<Company> companys){
        try{
            // Создание объекта DocumentBuilderFactory и DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Создание нового документа XML
            Document document = builder.newDocument();

            // Создание корневого элемента и добавление его в документ
            Element rootElement = document.createElement("companys");
            document.appendChild(rootElement);
            for(Company company: companys){
                Element compElement = document.createElement("company");
                rootElement.appendChild(compElement);
                Element compIdElement = document.createElement("id");
                compIdElement.appendChild(document.createTextNode(String.valueOf(company.getCompany_id())));
                compElement.appendChild(compIdElement);

            // Добавление элементов для полей объекта Company
                Element nameElement = document.createElement("name");
                nameElement.appendChild(document.createTextNode(company.getName()));
                compElement.appendChild(nameElement);
                List<Item> itemList = company.getItems();
                for (Item item : itemList) {
                    Element itemElement = document.createElement("item");
                    compElement.appendChild(itemElement);

                    Element idElement = document.createElement("id");
                    idElement.appendChild(document.createTextNode(String.valueOf(item.getItem_id())));
                    itemElement.appendChild(idElement);

                    Element nameItemElement = document.createElement("nameItem");
                    nameItemElement.appendChild(document.createTextNode(item.getName()));
                    itemElement.appendChild(nameItemElement);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("reportcomps.xml"));
            transformer.transform(source, result);

            System.out.println("XML-файл успешно создан!");
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}
