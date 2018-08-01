package Controller;

import FileHelper.CSVReader;
import FileHelper.CSVWriter;
import FileHelper.ReadExcel2007;
import Service.CompanyService;
import Service.ICompanyService;
import Model.Company;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Model.ConnectionSettings.tempFilePath;

public class Controller {

    ICompanyService companyService = new CompanyService();
    ReadExcel2007 readExcel2007 = new ReadExcel2007();

    public String openFilePath = "noData";
    List<String> links = new ArrayList<>();
    List<Company> savedCompanies = new ArrayList<>();

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


        links = readCSV(openFilePath);

        for (String link : links){

            String currentName = companyService.getName(link);
            String currentAddress = companyService.getAddress(link);
            String currentWebsite = companyService.getWebsite(tempFilePath);
            String currentContactPerson = "noData";
            String currentEmail = companyService.getEmail(link);
            String currentPhoneNunber = companyService.getPhoneNumber(link);

            Company currentCompany = new Company(currentName, currentAddress, currentWebsite,
                    currentContactPerson, currentEmail, currentPhoneNunber);

            savedCompanies.add(currentCompany);
            System.out.println(savedCompanies);
        }

    }

    public void saveToCSV() {
        csvWriter.saveCSV(csvWriter.prepareStringToCSV(savedCompanies));
    }
}
