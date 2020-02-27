package model.service.parser.investing;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import model.models.DataForCalculation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Парсит данные о компании для расчетов
public class DataForCalculationParser {

    public DataForCalculationParser() {
    }

    public List<DataForCalculation> getDataForCalculation(String searchString) {
        List<DataForCalculation> dataForCalculations = new ArrayList<>();

        //Получение нужных страниц поиска, так как нужная информация расположена на разных страницах
        String balance_sheet = searchString + "-balance-sheet";
        String financial_summary = searchString + "-financial-summary";
        String dividends = searchString + "-dividends";
        String prices = searchString + "-historical-data";

        try {

//Нажатие кнопки Annual с помощью HtmlUnit для страницы balance_sheet
            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            HtmlPage balancePage = webClient.getPage(balance_sheet);

            HtmlAnchor balanceAnchor = balancePage.getAnchorByText("Annual");
            if (balanceAnchor != null) {
                balanceAnchor.click();
                webClient.waitForBackgroundJavaScript(10000);
            }

            String htmlBalance = balancePage.asXml();
            Document balanceSheetDocument = Jsoup.parse(htmlBalance);
            //Получение нужных компонентов страницы balance_sheet для дальнейшего парсинга
            Element balanceSheetElement = balanceSheetDocument.getElementsByAttributeValue("id", "leftColumn").get(0);

/*Часть кода удалена*/

//Заполнение коллекции-результата данного метода объектами DataForCalculation
            if (balanceSheetElement != null) {
                //Сравнить название с тем, что ищем
                String companyName = balanceSheetElement.getElementsByTag("h1").first().text().trim();
                Element tableElement = balanceSheetElement.getElementsByAttributeValue("id", "rrtable").first();

                //коллекция для year
                Elements years = tableElement.getElementsByAttributeValue("class", "bold");
                //коллекция для dateOfReport
                Elements datesOfReport = tableElement.select("tr").get(0).select("th");

                int elementsOfPageTable = tableElement.select("tr").size();

                //коллекция для total_common_shares_outstanding
                //Elements rowWithCommonShares = tableElement.select("tr").get(51).select("td");

                Elements tableTrElements = tableElement.select("tr");
                //коллекция для total_preferred_shares_outstanding
                Elements rowWithPrefferedShares = null;
                //коллекция для total_common_shares_outstanding
                Elements rowWithCommonShares = null;
                if (tableTrElements != null) {
                    rowWithPrefferedShares = tableTrElements.last().select("td"); //последний элемент
                    rowWithCommonShares = tableTrElements.get(tableTrElements.size() - 2).select("td");//предпоследний элемент
                }


                //коллекция для current_assets
                //Elements rowWithCurrentAssets = tableElement.select("tr").get(1).select("td");
                Elements rowWithCurrentAssets = null;
                if (elementsOfPageTable >= 2) {
                    rowWithCurrentAssets = tableElement.select("tr").get(1).select("td");
                }

                //коллекция для total_inventory
                Elements rowWithInventory = null;
                //коллекция для cash
                Elements rowWithCash = null;
                //коллекция для total_current_liabilities
                Elements rowWithCurrentLiabilities = null;
                if (!rowWithCurrentAssets.get(1).text().equals("-")) {
                    if (elementsOfPageTable >= 10) {
                        rowWithInventory = tableElement.select("tr").get(9).select("td");
                    }
                    if (elementsOfPageTable >= 5) {
                        rowWithCash = tableElement.select("tr").get(4).select("td");
                    }
                    if (elementsOfPageTable >= 24) {
                        rowWithCurrentLiabilities = tableElement.select("tr").get(23).select("td");
                    }
                } else {
                    if (elementsOfPageTable >= 16) {
                        rowWithCurrentLiabilities = tableElement.select("tr").get(15).select("td");
                    }
                }

//Из страницы financial-summary
                //коллекция для total equity
                Elements rowWithTotalEquity = null;
                //коллекция для total liabilities
                Elements rowWithTotalLiabilities = null;
                if (financialSummaryElement2 != null) {
                    rowWithTotalEquity = financialSummaryElement2.select("tr").last().select("td");
                    rowWithTotalLiabilities = financialSummaryElement2.select("tr").get(2).select("td");
                }

                //коллекция для total_revenue
                Elements rowWithTotalRevenue = null;
                //коллекция для net_income
                Elements rowWithNetIncome = null;
                if (financialSummaryElement != null) {
                    rowWithTotalRevenue = financialSummaryElement.select("tr").get(1).select("td");
                    rowWithNetIncome = financialSummaryElement.select("tr").last().select("td");
                }

                //На страницах сайта информация выводится по последним 4 периодам
                for (int i = 0; i < 4; i++) {
                    DataForCalculation dataForCalculation = new DataForCalculation();

                    //ticker
                    String shareTicker = companyName.substring(companyName.indexOf("(")+1, companyName.indexOf(")"));
                    dataForCalculation.setTicker(shareTicker);

                    //year
                    String year = years.get(i).text();
                    dataForCalculation.setYear(Integer.parseInt(year));

                    //dateOfReport
                    String dateOfReport = datesOfReport.get(i+1).text();
                    dataForCalculation.setDateOfReport(dateOfReport);

                    //totalCommonSharesOutstanding
                    if (rowWithCommonShares != null) {
                        String total_common_shares_outstanding = rowWithCommonShares.get(i + 1).text();
                        if (total_common_shares_outstanding.equalsIgnoreCase("-"))
                            dataForCalculation.setTotalCommonSharesOutstanding(0);
                        else
                            dataForCalculation.setTotalCommonSharesOutstanding(Double.parseDouble(total_common_shares_outstanding));
                    } else dataForCalculation.setTotalCommonSharesOutstanding(0);

                    //totalPreferredSharesOutstanding
                    if (rowWithPrefferedShares != null) {
                        String total_preferred_shares_outstanding = rowWithPrefferedShares.get(i + 1).text();
                        if (total_preferred_shares_outstanding.equalsIgnoreCase("-"))
                            dataForCalculation.setTotalPreferredSharesOutstanding(0);
                        else
                            dataForCalculation.setTotalPreferredSharesOutstanding(Double.parseDouble(total_preferred_shares_outstanding));
                    } else dataForCalculation.setTotalPreferredSharesOutstanding(0);

                    //totalCurrentLiabilities
                    if (rowWithCurrentLiabilities != null) {
                        String total_current_liabilities = rowWithCurrentLiabilities.get(i + 1).text();
                        if (total_current_liabilities.equalsIgnoreCase("-"))
                            dataForCalculation.setTotalCurrentLiabilities(0);
                        else
                            dataForCalculation.setTotalCurrentLiabilities(Double.parseDouble(total_current_liabilities));
                    } else dataForCalculation.setTotalCurrentLiabilities(0);

                    //currentAssets
                    if (rowWithCurrentAssets != null) {
                        String current_assets = rowWithCurrentAssets.get(i + 1).text();
                        if (current_assets.equalsIgnoreCase("-")) dataForCalculation.setCurrentAssets(0);
                        else dataForCalculation.setCurrentAssets(Double.parseDouble(current_assets));
                    } else dataForCalculation.setCurrentAssets(0);

                    //totalInventory
                    if (rowWithInventory != null) {
                        String total_inventory = rowWithInventory.get(i + 1).text();
                        if (total_inventory.equalsIgnoreCase("-")) dataForCalculation.setTotalInventory(0);
                        else dataForCalculation.setTotalInventory(Double.parseDouble(total_inventory));
                    } else dataForCalculation.setTotalInventory(0);

                    //cash
                    if (rowWithCash != null) {
                        String cash = rowWithCash.get(i + 1).text();
                        if (cash.equalsIgnoreCase("-")) dataForCalculation.setCash(0);
                        else dataForCalculation.setCash(Double.parseDouble(cash));
                    } else dataForCalculation.setCash(0);

                    //totalEquity
                    if (rowWithTotalEquity != null) {
                        String total_equity = rowWithTotalEquity.get(i + 1).text();
                        if (total_equity.equalsIgnoreCase("-")) dataForCalculation.setTotalEquity(0);
                        else dataForCalculation.setTotalEquity(Double.parseDouble(total_equity));
                    } else dataForCalculation.setTotalEquity(0);

                    //totalLiabilities
                    if (rowWithTotalLiabilities != null) {
                        String total_liabilities = rowWithTotalLiabilities.get(i + 1).text();
                        if (total_liabilities.equalsIgnoreCase("-")) dataForCalculation.setTotalLiabilities(0);
                        else dataForCalculation.setTotalLiabilities(Double.parseDouble(total_liabilities));
                    } else dataForCalculation.setTotalLiabilities(0);

                    //totalRevenue
                    if (rowWithTotalRevenue != null) {
                        String total_revenue = rowWithTotalRevenue.get(i + 1).text();
                        if (total_revenue.equalsIgnoreCase("-")) dataForCalculation.setTotalRevenue(0);
                        else dataForCalculation.setTotalRevenue(Double.parseDouble(total_revenue));
                    } else dataForCalculation.setTotalRevenue(0);

                    //netIncome
                    if (rowWithNetIncome != null) {
                        String net_income = rowWithNetIncome.get(i + 1).text();
                        if (net_income.equalsIgnoreCase("-")) dataForCalculation.setNetIncome(0);
                        else dataForCalculation.setNetIncome(Double.parseDouble(net_income));
                    } else dataForCalculation.setNetIncome(0);
                    //Добавление полученого элемента данных в коллекцию данных
                    dataForCalculations.add(dataForCalculation);
                }

//Информация о дивидендах и цена вносится в данные позже остальной информации, потому что она нужна для конкретного
//рассматриваемого года. А для этого нужны года, занесенные в данные в цикле выше

                //dividend
                //Если на странице есть инфа по дивидендам
                if (dividendsElement != null) {
                    //коллекция для dividend - элементы страницы с нужной инфой
                    Elements rowWithDividends = dividendsElement.select("tr");

                    //Заесение в данные информации по дивидендам по годам
                    for (DataForCalculation dataForCalculation : dataForCalculations) {
                        String yearFromData = "" + dataForCalculation.getYear();
                        for (int i = 1; i < rowWithDividends.size(); i++) {
                            Element element = rowWithDividends.get(i);
                            if (element.select("td").get(0).text().contains(yearFromData)) {
                                String div = element.select("td").get(1).text();
                                dataForCalculation.setDividend(dataForCalculation.getDividend() + Double.parseDouble(div));
                            }
                        }
                    }
                }
            }

            //Занесение информации по ценам по годам 
//Нажатие кнопки Monthly и выбор периода с помощью HtmlUnit для страницы prices
            int min = Integer.MAX_VALUE;
            for (DataForCalculation dataForCalculation : dataForCalculations) {
                if (dataForCalculation.getYear() < min) min = dataForCalculation.getYear();
            }

            balancePage = webClient.getPage(prices);
//Нажатие на "Monthly" (3-й из элементов в данном элементе) из выпадающего списка для выбора периодичности отображения
            HtmlElement g = balancePage.getElementById("data_interval").getElementsByTagName("option").get(2);
            if (g != null) {
                g.click();
                webClient.waitForBackgroundJavaScript(10000);
            }
            //Изменение временного периода отображения цен на акции
            String pickerElementValue = balancePage.getElementById("picker").getAttribute("value");
            balancePage.getElementById("picker").setAttribute("value", "01/01/" + (min-1) + " - " + pickerElementValue.substring(13));
//Нажатие на странице кнопки для раскрытия виджета календаря для задания периода, чтобы получить в коде страницы элемента кнопки "Apply"
            HtmlElement h = balancePage.getHtmlElementById("column-content").getElementsByAttribute("span", "class", "datePickerIcon").get(0);
            if (h != null) {
                h.click();
                webClient.waitForBackgroundJavaScript(5000);
            }
            HtmlElement k = balancePage.getHtmlElementById("applyBtn");
            if (k != null) {
                k.click();
                webClient.waitForBackgroundJavaScript(20000);
            }
            //Теперь страница отображает данные по месяцам в нужном периоде
            String htmlPrices = balancePage.asXml();

            webClient.close();

            Document pricesDocument = Jsoup.parse(htmlPrices);

            Element pricesElement = pricesDocument.getElementById("curr_table");

            if (pricesElement != null) {
                //коллекция для prices элементов страницы
                Elements rowWithPrices = pricesElement.select("tr");

                //Вносим в данные информацию за каждый год из уже имеющихся
                for (DataForCalculation dataForCalculation : dataForCalculations) {
                    String yearFromData = "" + dataForCalculation.getYear();
                    for (int i = 1; i < rowWithPrices.size(); i++) {
                        Element element = rowWithPrices.get(i);
                        //Ценой за год принята цена на декабрь года отчетности
                        if (element.select("td").get(0).text().contains("Dec " + yearFromData.substring(2))) {
                            String price = element.select("td").get(1).text().replaceAll(",", "");
                            dataForCalculation.setPrice(Double.parseDouble(price));
                        }
                    }
                }
            }

        } catch (IOException e) {

        }

        return dataForCalculations;
    }

}
