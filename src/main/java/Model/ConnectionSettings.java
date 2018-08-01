package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ConnectionSettings {

    public static String companyInfoCard = "noData";
    public static String companyNameCSSselector = ".company-name";
    public static String companyAddressCSSselector = "div.col-sm-8:nth-child(1) > div:nth-child(1)";
    public static String companyPhoneCSSselector = ".col-www > a:nth-child(2)";
    public static String webpageLineValidator = "nofollow";
    public static String emailLineValidator = "mailto:";
    public static String tempFilePath = "src/main/resources/tmp/page.txt";
}
