package FileHelper;

import Model.Company;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVWriter {

    public String prepareStringToCSV(List<Company> list) {
        char separator = ';';
        String nextLine = "\n";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("name").append(separator)
                .append("address").append(separator)
                .append("website").append(separator)
                .append("contactPerson").append(separator)
                .append("email").append(separator)
                .append("phoneNumber").append(separator)
                .append(nextLine);

        for (Company company : list) {
            stringBuilder.append(company.getName()).append(separator)
                    .append(company.getAddress()).append(separator)
                    .append(company.getWebsite()).append(separator)
                    .append(company.getContactPerson()).append(separator)
                    .append(company.getEmail()).append(separator)
                    .append(company.getPhoneNumber()).append(separator)
                    .append(nextLine);
        }
        return stringBuilder.toString();
    }

    public void saveCSV(String text) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plik CSV (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);

        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CSVWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void saveCSV_UTF8(String text){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plik CSV (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);

        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF8"));
            out.write(text);
            out.flush();
            out.close();
        }
        catch (UnsupportedEncodingException e)
        {
            System.out.println(e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


}

