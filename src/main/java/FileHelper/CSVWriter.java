package FileHelper;

import Model.Company;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
        File file = fileChooser.showSaveDialog(null);



    }
}

