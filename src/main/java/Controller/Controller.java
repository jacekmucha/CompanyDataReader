package Controller;

import FileHelper.*;
import Service.*;
import Model.Company;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.apache.commons.csv.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static Model.ConnectionSettings.tempFilePath;

public class Controller {

    public Controller() {
        prepareTable();
    }

    @FXML
    public TableView table = new TableView();

    @FXML
    public Label currentFileLine = new Label();
    @FXML
    public Label fileLines = new Label();


    ICompanyService companyService = new CompanyService();
    ReadExcel2007 readExcel2007 = new ReadExcel2007();

    public String openFilePath = "noData";
    List<String> links = new ArrayList<>();
    List<Company> savedCompanies = new ArrayList<>();

    final ObservableList<Company> observableList = FXCollections.observableArrayList();

    CSVReader csvReader = new CSVReader();
    CSVWriter csvWriter = new CSVWriter();


    public void openFile() {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            System.out.println("Wybrany plik: " + file.getName());
            System.out.println(file.getAbsolutePath());
        }
        openFilePath = file.getPath();

    }


    public List<String> readCSV(String path) {

        List<String> importedLinks = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'))
        ) {
            for (CSVRecord csvRecord : csvParser) {
                String link = csvRecord.get(0);
                importedLinks.add(link);
                System.out.println(link);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return importedLinks;
    }

    @FXML
    public void getDataByLink() {

    }

    @FXML
    public void downloadCompanyData() throws IOException {

        int size = links.size();
        fileLines.setText(Integer.toString(size));

        Integer counter = 0;

        try {
            links = readCSV(openFilePath);

            for (String link : links) {
                companyService.downloadCompanyCard(link);

                String currentName = companyService.getName(link);
                String currentAddress = companyService.getAddress(link);
                String currentWebsite = companyService.getWebsite(tempFilePath);
                String currentContactPerson = "notImplementedMethod: getContactPerson";
                String currentEmail = companyService.getEmail(link);
                String currentPhoneNunber = companyService.getPhoneNumber(link);

                Company currentCompany = new Company(currentName, currentAddress, currentWebsite,
                        currentContactPerson, currentEmail, currentPhoneNunber);

                savedCompanies.add(currentCompany);
                observableList.add(currentCompany);

                counter++;
                currentFileLine.setText(Integer.toString(counter));

            }

            System.out.println(savedCompanies);
            System.out.println("\n\n Liczba rekordów: " + savedCompanies.size());


        } catch (java.nio.file.NoSuchFileException e) {
            System.out.println("Nie wczytano pliku CSV!");
        }


    }

    public void saveToCSV() {
        String content = csvWriter.prepareStringToCSV(observableList);
        csvWriter.saveCSV_UTF8(content);
    }

    public void prepareTable(){
        table.setEditable(true);

        TableColumn nameColumn = new TableColumn("Nazwa firmy");
        TableColumn addressColumn = new TableColumn("Adres firmy");
        TableColumn wwwColumn = new TableColumn("WWW");
        TableColumn emailColumn = new TableColumn("Adres Email");
        TableColumn phoneColumn = new TableColumn("Nr. telefonu");

        nameColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("Nazwa firmy"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("Adres firmy"));
        wwwColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("WWW"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("Adres Email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Company, String>("Nr. telefonu"));

        table.setItems(observableList);
        table.getColumns().addAll(nameColumn,addressColumn,wwwColumn,emailColumn,phoneColumn);
    }

}
