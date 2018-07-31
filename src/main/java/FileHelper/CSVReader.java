package FileHelper;

import Controller.Controller;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

//    Controller controller = new Controller();

//    public List<String> readCSV(String path){
//
//                List<String> importedLinks = new ArrayList<>();
//
//        try (
//                Reader reader = Files.newBufferedReader(Paths.get(path));
//                CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'))
//        ) {
//            for (CSVRecord csvRecord : csvParser) {
//                String link = csvRecord.get(0);
//
//                importedLinks.add(link);
//                System.out.println(link);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return importedLinks;
//    }




}
