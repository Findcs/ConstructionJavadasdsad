package ru.anton.test2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.anton.test2.facade.SQL;
import ru.anton.test2.models.Company;
import ru.anton.test2.models.Item;
import ru.anton.test2.models.User;
import ru.anton.test2.models.Views;
import ru.anton.test2.repository.CompanyRepository;
import ru.anton.test2.repository.DescriptionRepository;
import ru.anton.test2.repository.ItemRepository;
import ru.anton.test2.repository.UserRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import org.springframework.web.bind.annotation.RestController;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Service
@AllArgsConstructor
public class ReportService {

    private CompanyRepository companyRepository;
    private ItemRepository itemRepository;

    private ItemService itemService;

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

    private String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Element tagElement = (Element) nodeList.item(0);
        return tagElement.getTextContent();
    }

    public Company importXML(String fileName){
        try {
            // Создание объекта DocumentBuilderFactory и DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Чтение XML-файла и создание объекта Document
            Document document = builder.parse(new File(fileName));
            // Получение корневого элемента
            Element rootElement = document.getDocumentElement();
            // Получение элементов для полей объекта Company
            String name = getElementTextContent(rootElement, "name");
            int comp_id = Integer.parseInt(getElementTextContent(rootElement, "id"));
            // Получение элементов для списка объектов Item
            NodeList itemList = rootElement.getElementsByTagName("item");
            List<Item> items = new ArrayList<>();
            for (int i = 0; i < itemList.getLength(); i++) {
                Element itemElement = (Element) itemList.item(i);
                int item_id = Integer.parseInt(getElementTextContent(itemElement, "id"));
                String nameItem = getElementTextContent(itemElement, "nameItem");
                Item item = new Item();
                item.setItem_id(item_id);
                item.setName(nameItem);
                items.add(item);
            }
            Company company = new Company();
            company.setName(name);
            company.setItems(items);
            System.out.println(company);
            Optional<Company> company_bd = companyRepository.findByName(name);
            if (company_bd.isEmpty())
                companyRepository.save(company);
            for (Item item: items){
                itemService.add_item(item.getName(),name);
            }
            return company;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createExcelFromDate(List<Views> views, LocalDate date){
        // Создание нового документа Excel
        Workbook workbook = new XSSFWorkbook();

        // Создание нового листа и добавление его в документ
        Sheet sheet = workbook.createSheet("Views");

        // Создание заголовков столбцов
        Row headerRow = sheet.createRow(0);
        Cell dayHeaderCell = headerRow.createCell(0);
        dayHeaderCell.setCellValue("Дата");

        Cell itemHeaderCell = headerRow.createCell(1);
        itemHeaderCell.setCellValue("Айтем");

        Cell viewsHeaderCell = headerRow.createCell(2);
        viewsHeaderCell.setCellValue("Количество просмотров");
        int rowNum = 1;
        for (Views viewsObj : views) {
            if(viewsObj.getDate().compareTo(date) >= 0) {
                Row row = sheet.createRow(rowNum++);
                // Заполнение ячейки "Дата"
                Cell dayCell = row.createCell(0);
                dayCell.setCellValue(viewsObj.getDate().toString());

                // Заполнение ячейки "Айтем"
                Cell itemCell = row.createCell(1);
                itemCell.setCellValue(viewsObj.getItem().getName());

                // Заполнение ячейки "Количество просмотров"
                Cell viewsCell = row.createCell(2);
                viewsCell.setCellValue(viewsObj.getViews());
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(views.get(0).getItem().getName()+"from"+date.toString()+".xls", false)) {
            workbook.write(outputStream);
            System.out.println("Таблица Excel успешно создана!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createExcelFromDateFromHigh(List<Views> views, LocalDate date, int filtr){
        // Создание нового документа Excel
        Workbook workbook = new XSSFWorkbook();

        // Создание нового листа и добавление его в документ
        Sheet sheet = workbook.createSheet("Views");

        // Создание заголовков столбцов
        Row headerRow = sheet.createRow(0);
        Cell dayHeaderCell = headerRow.createCell(0);
        dayHeaderCell.setCellValue("Дата");

        Cell itemHeaderCell = headerRow.createCell(1);
        itemHeaderCell.setCellValue("Айтем");

        Cell viewsHeaderCell = headerRow.createCell(2);
        viewsHeaderCell.setCellValue("Количество просмотров");
        Collections.sort(views, new Comparator<Views>() {
            @Override
            public int compare(Views v1, Views v2) {
                return Integer.compare(v2.getViews(), v1.getViews());
            }
        });
        int rowNum = 1;
        for (Views viewsObj : views) {
            if(viewsObj.getDate().compareTo(date) >= 0 && viewsObj.getViews()>filtr) {
                Row row = sheet.createRow(rowNum++);
                // Заполнение ячейки "Дата"
                Cell dayCell = row.createCell(0);
                dayCell.setCellValue(viewsObj.getDate().toString());

                // Заполнение ячейки "Айтем"
                Cell itemCell = row.createCell(1);
                itemCell.setCellValue(viewsObj.getItem().getName());

                // Заполнение ячейки "Количество просмотров"
                Cell viewsCell = row.createCell(2);
                viewsCell.setCellValue(viewsObj.getViews());
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream("All items from"+date.toString()+".xls", false)) {
            workbook.write(outputStream);
            System.out.println("Таблица Excel успешно создана!");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
