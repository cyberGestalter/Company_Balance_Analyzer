package model.service.parser.investing;

import model.models.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Парсит общую инфу о компании
public class CompanyParser {
    Document doc;

    public CompanyParser() {
    }

    public List<Company> getCompanies(String searchString) {
        List<Company> companies = new ArrayList<>();

        try {
            doc = getDocument(searchString);
            Elements elements = doc.getElementsByAttributeValue("id", "leftColumn");
            for (Element element : elements) {
                if (element != null) {
                    Company company = new Company();
                    String companyName = element.getElementsByTag("h1").first().text().trim();
                    if (companyName.contains("\'")) companyName = companyName.substring(0, companyName.indexOf("\'"))+"\\"+companyName.substring(companyName.indexOf("\'"));
                    company.setCompanyName(companyName);
                    String shareTicker = companyName.substring(companyName.indexOf("(")+1, companyName.indexOf(")"));
                    company.setTicker(shareTicker);
                    String companyIndustry = null;
                    try {
                        companyIndustry = element.getElementsByAttributeValue("class", "companyProfileHeader").first().getElementsByTag("div").first().getElementsByTag("a").first().text();
                    } catch (NullPointerException e) {
                        continue;
                    }
                    company.setCompanyIndustry(companyIndustry);

                    companies.add(company);
                }
            }

       } catch (IOException e) {

        }

        return companies;
    }

    public Document getDocument(String searchString) throws IOException {
        String userAgent = "Юзер агент";
        String referrer = "";
        Document document = Jsoup.connect(searchString).userAgent(userAgent).referrer(referrer).get();
        return document;
    }
}
