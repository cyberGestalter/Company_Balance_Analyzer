package model.models;

//Класс, описывающий первичные финансовые данные о компании, собираемые из сети
public class DataForCalculation extends BaseModel {
    /*
    Price - Цена акции
	Dividend - Дивиденд
	Net income - Чистая прибыль
	Total revenue - Выручка (объем продаж)
	Total equity - Собственный капитал
	Total Common Shares Outstanding - Число обыкновенных акций
	Total Preferred Shares Outstanding - Число привилегированных акций
	Total liabilities - Долгосрочные долговые обязательства
	Total current liabilities - Краткосрочные долговые (текущие) обязательства
	Current assets - текущие активы
	Total inventory - Материальные запасы
	Cash - Наличный капитал
    */

    private String ticker;
    //год отчетности
    private int year;
    //Дата отчетности в формате год день/месяц
    private String dateOfReport;

    public String getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(String dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    private double price;
    private double dividend = 0;
    private double netIncome;
    private double totalRevenue;
    private double totalEquity;
    private double totalCommonSharesOutstanding;
    private double totalPreferredSharesOutstanding;
    private double totalLiabilities;
    private double totalCurrentLiabilities;
    private double currentAssets;
    private double totalInventory;
    private double cash;

    public DataForCalculation() { }

    public DataForCalculation(String ticker, int year, String dateOfReport, double price, double dividend, double netIncome,
                              double totalRevenue, double totalEquity, double totalCommonSharesOutstanding,
                              double totalPreferredSharesOutstanding, double totalLiabilities,
                              double totalCurrentLiabilities, double currentAssets, double totalInventory, double cash) {
        this.ticker = ticker;
        this.year = year;
        this.dateOfReport = dateOfReport;
        this.price = price;
        this.dividend = dividend;
        this.netIncome = netIncome;
        this.totalRevenue = totalRevenue;
        this.totalEquity = totalEquity;
        this.totalCommonSharesOutstanding = totalCommonSharesOutstanding;
        this.totalPreferredSharesOutstanding = totalPreferredSharesOutstanding;
        this.totalLiabilities = totalLiabilities;
        this.totalCurrentLiabilities = totalCurrentLiabilities;
        this.currentAssets = currentAssets;
        this.totalInventory = totalInventory;
        this.cash = cash;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDividend() {
        return dividend;
    }

    public void setDividend(double dividend) {
        this.dividend = dividend;
    }

    public double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(double netIncome) {
        this.netIncome = netIncome;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public double getTotalEquity() {
        return totalEquity;
    }

    public void setTotalEquity(double totalEquity) {
        this.totalEquity = totalEquity;
    }

    public double getTotalCommonSharesOutstanding() {
        return totalCommonSharesOutstanding;
    }

    public void setTotalCommonSharesOutstanding(double totalCommonSharesOutstanding) {
        this.totalCommonSharesOutstanding = totalCommonSharesOutstanding;
    }

    public double getTotalPreferredSharesOutstanding() {
        return totalPreferredSharesOutstanding;
    }

    public void setTotalPreferredSharesOutstanding(double totalPreferredSharesOutstanding) {
        this.totalPreferredSharesOutstanding = totalPreferredSharesOutstanding;
    }

    public double getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public double getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }

    public void setTotalCurrentLiabilities(double totalCurrentLiabilities) {
        this.totalCurrentLiabilities = totalCurrentLiabilities;
    }

    public double getCurrentAssets() {
        return currentAssets;
    }

    public void setCurrentAssets(double currentAssets) {
        this.currentAssets = currentAssets;
    }

    public double getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(double totalInventory) {
        this.totalInventory = totalInventory;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "DataForCalculation{" +
                "ticker='" + ticker + "\n" +
                ", year=" + year + "\n" +
                ", dateOfReport=" + dateOfReport + "\n" +

                ", netIncome=" + netIncome + "\n" +
                ", totalRevenue=" + totalRevenue + "\n" +
                ", totalEquity=" + totalEquity + "\n" +
                ", totalLiabilities=" + totalLiabilities + "\n" +

                ", totalCommonSharesOutstanding=" + totalCommonSharesOutstanding + "\n" +
                ", totalPreferredSharesOutstanding=" + totalPreferredSharesOutstanding + "\n" +
                ", totalCurrentLiabilities=" + totalCurrentLiabilities + "\n" +
                ", currentAssets=" + currentAssets + "\n" +
                ", totalInventory=" + totalInventory + "\n" +
                ", cash=" + cash + "\n" +

                ", dividend=" + dividend + "\n" +

                ", price=" + price + "\n" +
                '}';
    }
}
