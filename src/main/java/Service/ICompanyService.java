package Service;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface ICompanyService {

    Document prepareDocument(String link);

    void downloadCompanyCard(String link);

    String getName(String link);

    String getAddress(String link);

    String getWebsite(String tempFilePath) throws IOException;

    String getEmail(String link) throws IOException;

    String getPhoneNumber(String link);

}
