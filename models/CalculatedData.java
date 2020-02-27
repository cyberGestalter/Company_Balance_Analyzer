package model.models;

//Класс, описывающий данные, рассчитываемые на основе первичных финансовых данных
public class CalculatedData extends BaseModel {

    private String ticker;
    private int year;

    //Earning per share - Прибыль на акцию за год (EPS = Чистая прибыль/Число обыкновенных акций)
    private double eps;
    //Capitalization - Капитализация (= Цена акции * Число обыкновенных акций)
    private double capitalization;
    // Р/Е: CompanyShare - Price/Earning per share
    private double priceToEPS;
    /*Для растущих компаний:
    income speed rate - Средний годовой прирост прибыли, %: (Net income(this year) - net income(last year))/net income(last year) * 100
    equitable price - Справедливая цена акции: income speed rate * Earning per share
    */
    private double incomeSpeedRate;
    private double equitablePrice;
    /*Дивиденды:
    Dividend payout - Дивидендный выход(PR), %: Dividend / Earning per share * 100
    PR по отрасли, %:  Где-то нужно взять
    */
    private double dividendPayout;
    private double industryDividendPayout;
    /*Рентабельность:
    Рентабельность акции, %: Dividend / Price * 100
    */
    private double share_ROE;
    /*Прибыль и выручка:
    EPS speed rate - Темп роста прибыли на акцию, %: (Earning per share(this year) - Earning per share(last year))/Earning per share(last year) * 100
    Total revenue speed rate - Темп роста объема продаж, %: (Total revenue(this year) - Total revenue(last year))/Total revenue(last year) * 100
    */
    private double epsSpeedRate;
    private double totalRevenueSpeedRate;
    /*
    Коэффициенты:
    BV - Book Value - Учетная цена акции (BV): Total equity/Total Common Shares Outstanding
	Price/Book Value Ratio (Stock quote rate) - Коэффициент котировки акции (p/b): Price / BV

	PM - Profit Margin - Рентабельность (РМ), %: Net income / Total revenue * 100

	SPS - Sales Per Share - Объем продаж на одну акцию (SPS): Total revenue / Total Common Shares Outstanding
	p/s: Price / SPS

	ROE - Рентабельность собственного капитала (ROE), %: Net income / Total equity * 100
    */
    private double bookValue;
    private double priceToBookValue;
    private double profitMargin;
    private double salesPerShare;
    private double priceToSPS;
    private double roe;
    /*Долги:
    DOE - DOE, %:  Total liabilities / Total equity * 100

	Current ratio - Коэффициент покрытия(ликвидности, CR) Норма > 2: Current assets/Total current liabilities

	Quick ratio - Коэффициент быстрой ликвидности (QR) Норма >=1: (Current assets - Total inventory)/Total current liabilities

	p/c - Цена акции к капиталу на одну акцию, р/с Норма < 0,1: Price / (Cash / Total Common Shares Outstanding)
    */
    private double doe;
    private double currentRatio;
    private double quickRatio;
    private double priceToCash;

    public CalculatedData() {
    }

    public CalculatedData(String ticker, int year, double eps, double capitalization, double priceToEPS,
                          double incomeSpeedRate, double equitablePrice, double dividendPayout,
                          double industryDividendPayout, double share_ROE, double epsSpeedRate,
                          double totalRevenueSpeedRate, double bookValue, double priceToBookValue,
                          double profitMargin, double salesPerShare, double priceToSPS, double roe, double doe,
                          double currentRatio, double quickRatio, double priceToCash) {
        this.ticker = ticker;
        this.year = year;
        this.eps = eps;
        this.capitalization = capitalization;
        this.priceToEPS = priceToEPS;
        this.incomeSpeedRate = incomeSpeedRate;
        this.equitablePrice = equitablePrice;
        this.dividendPayout = dividendPayout;
        this.industryDividendPayout = industryDividendPayout;
        this.share_ROE = share_ROE;
        this.epsSpeedRate = epsSpeedRate;
        this.totalRevenueSpeedRate = totalRevenueSpeedRate;
        this.bookValue = bookValue;
        this.priceToBookValue = priceToBookValue;
        this.profitMargin = profitMargin;
        this.salesPerShare = salesPerShare;
        this.priceToSPS = priceToSPS;
        this.roe = roe;
        this.doe = doe;
        this.currentRatio = currentRatio;
        this.quickRatio = quickRatio;
        this.priceToCash = priceToCash;
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

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(double capitalization) {
        this.capitalization = capitalization;
    }

    public double getPriceToEPS() {
        return priceToEPS;
    }

    public void setPriceToEPS(double priceToEPS) {
        this.priceToEPS = priceToEPS;
    }

    public double getIncomeSpeedRate() {
        return incomeSpeedRate;
    }

    public void setIncomeSpeedRate(double incomeSpeedRate) {
        this.incomeSpeedRate = incomeSpeedRate;
    }

    public double getEquitablePrice() {
        return equitablePrice;
    }

    public void setEquitablePrice(double equitablePrice) {
        this.equitablePrice = equitablePrice;
    }

    public double getDividendPayout() {
        return dividendPayout;
    }

    public void setDividendPayout(double dividendPayout) {
        this.dividendPayout = dividendPayout;
    }

    public double getIndustryDividendPayout() {
        return industryDividendPayout;
    }

    public void setIndustryDividendPayout(double industryDividendPayout) {
        this.industryDividendPayout = industryDividendPayout;
    }

    public double getShare_ROE() {
        return share_ROE;
    }

    public void setShare_ROE(double share_ROE) {
        this.share_ROE = share_ROE;
    }

    public double getEpsSpeedRate() {
        return epsSpeedRate;
    }

    public void setEpsSpeedRate(double epsSpeedRate) {
        this.epsSpeedRate = epsSpeedRate;
    }

    public double getTotalRevenueSpeedRate() {
        return totalRevenueSpeedRate;
    }

    public void setTotalRevenueSpeedRate(double totalRevenueSpeedRate) {
        this.totalRevenueSpeedRate = totalRevenueSpeedRate;
    }

    public double getBookValue() {
        return bookValue;
    }

    public void setBookValue(double bookValue) {
        this.bookValue = bookValue;
    }

    public double getPriceToBookValue() {
        return priceToBookValue;
    }

    public void setPriceToBookValue(double priceToBookValue) {
        this.priceToBookValue = priceToBookValue;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }

    public double getSalesPerShare() {
        return salesPerShare;
    }

    public void setSalesPerShare(double salesPerShare) {
        this.salesPerShare = salesPerShare;
    }

    public double getPriceToSPS() {
        return priceToSPS;
    }

    public void setPriceToSPS(double priceToSPS) {
        this.priceToSPS = priceToSPS;
    }

    public double getRoe() {
        return roe;
    }

    public void setRoe(double roe) {
        this.roe = roe;
    }

    public double getDoe() {
        return doe;
    }

    public void setDoe(double doe) {
        this.doe = doe;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(double currentRatio) {
        this.currentRatio = currentRatio;
    }

    public double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(double quickRatio) {
        this.quickRatio = quickRatio;
    }

    public double getPriceToCash() {
        return priceToCash;
    }

    public void setPriceToCash(double priceToCash) {
        this.priceToCash = priceToCash;
    }
}
