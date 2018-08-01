package Service;

import java.io.File;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Model.ConnectionSettings.*;

public class CompanyService implements ICompanyService {

    String noData = "UWAGA! BRAK DANYCH!";


    @Override
    public Document prepareDocument(String link) {
        Document document = null;

        Connection connect = Jsoup.connect(link);
        try {
            document = connect.get();

        } catch (IOException e) {
            System.out.println("Błąd połączenia do źródła danych!");
        }
        return document;
    }

    @Override
    public void downloadCompanyCard(String link) {
        BufferedWriter bw = null;

        try {
            File file = new File(tempFilePath);
            file.createNewFile();

            URL website = new URL(link);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(website.openStream()));

            bw = new BufferedWriter(new FileWriter(file));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                bw.write(inputLine);
                bw.newLine();
            }
            in.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter " + ex);
            }
        }
    }

    @Override
    public String getName(String link) {
        String name = "noData";

        Elements companyName = prepareDocument(link).select(companyNameCSSselector);
        for (Element e : companyName) {
            name = e.text();
        }
        return name;
    }

    @Override
    public String getAddress(String link) {
        String address = "noData";

        Elements companyAddress = prepareDocument(link).select(companyAddressCSSselector);
        for (Element e : companyAddress) {
            if (!e.text().contains("napisz email")) {
                address = e.text();
            } else {
                address = "BRAK DANYCH w polu adresu!";
            }
        } return address;
    }

    @Override
    public String getWebsite(String tempFilePath) throws IOException {
        String website = "noData";

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        String currentLine;
        int currentLineIndex = 0;
        int websiteLineIndex = 0;

        try {
            bufferedReader = new BufferedReader(new FileReader(tempFilePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while ((currentLine = bufferedReader.readLine()) != null) {
            if (!currentLine.contains(webpageLineValidator)) {
                currentLineIndex++;
            } else if (currentLine.contains(webpageLineValidator)) {
                websiteLineIndex = currentLineIndex - 2;
                break;
            }
        }

        String validatedLine = null;
        try {
            validatedLine = Files.readAllLines(Paths.get(tempFilePath)).get(websiteLineIndex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String www = validatedLine.trim();
        String wwwFinal = www.substring(6, www.length() - 1);

        website = wwwFinal;
        return website;
    }

    @Override
    public String getEmail(String link) throws IOException {
        String email = noData;

        try {
            URL panoramaEmail = null;
            try {
                panoramaEmail = new URL(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(panoramaEmail.openStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String inputLine;
            String lineValidator = emailLineValidator;
            String emailLine = "brak danych";

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains(lineValidator)) {
                    emailLine = inputLine;
                    break;
                }
            }
            in.close();
            String emailTrimmed = emailLine.trim();

            int startEmail = 0;
            int endEmail = 0;
            String emailFinal;

            if (emailTrimmed.contains(";")) {
                emailTrimmed.split(";");

                for (String line : emailTrimmed.split(";")) {
                    if (line.contains(emailLineValidator)) {
                        startEmail = line.indexOf(":")+1;
                        endEmail = line.indexOf("&");
                        emailFinal = line.substring(startEmail, endEmail);

                        email = emailFinal;
                    }
                }

            } else {
                String emailToSubstring = emailTrimmed;
                startEmail = emailToSubstring.indexOf(":")+1;
                endEmail = emailToSubstring.length()-1;
                emailFinal = emailToSubstring.substring(startEmail, endEmail);

                email = emailFinal;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("*** Email - brak danych ***");
        }
        return email;
    }

    @Override
    public String getPhoneNumber(String link) {
        String phoneNumber = noData;

        Elements companyPhone = prepareDocument(link).select(companyPhoneCSSselector);

        if (companyPhone.size() < 1) {
            return phoneNumber;
        }

        else {
            for (Element e : companyPhone) {
                phoneNumber = e.text();
            }
        }
        return phoneNumber;
    }
}
