package FileHelper;

import Model.Company;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcel2007 {


    private static final String FILE_NAME = "/tmp/MyFirstExcel.xlsx";

    public void writeExcel(List<Company> companyList) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Export");

        ///////////////////////

        Object[][] exportData = {

                {"Nazwa firmy", "Adres firmy", "WWW", "Email", "Nr. telefonu"},

        };

        int rowNum = 0;
        System.out.println("Creating excel");

        for (Object[] data : exportData) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            for (Object field : data) {
                Cell cell = row.createCell(colNum++);

                if (field instanceof String) {
                    cell.setCellValue((String) field);
                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        ///////////////////////

        try {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

}
