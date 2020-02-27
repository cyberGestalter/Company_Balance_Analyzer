package model.models;

public class AnalysisResult {
    private String ticker;

    //Суммарная оценка перспективности компании
    private int promise;

    //Анализ дивидендов - критерий для выбора компаний в ОДНОЙ отрасли
    //1) Стабильная история выплат
    //2) Сравнение со средним дивидендным выходом по отрасли
    //share_roe, dividend_payout
    private String historyStability; //"хорошая"/ "плохая"
    private String industryLessMore; //"хорошо" - меньше PR по отрасли/ "плохо" - больше PR по отрасли

    //Анализ прибылей
    //1) p/e
    //2) Динамика объема продаж
    //3) Сравнение темпов роста продаж и прибыли
    //4) Меньшая капитализация
    private String priceToEPSvalueEvaluation;
    private String revenueDynamics;
    private String earningRevenueComparing;
    private double capitalization;

    //Анализ прочих финансовых показателей
    //1) B/V и p/b - сравниваются в пределах ОДНОЙ отрасли
    //2) PM
    //3) SPS и p/s
    //4) ROE
    //5) Динамика ROE
    //6) DOE
    //7) CR
    //8) QR
    //9) p/c
    private String bookValueEvaluation;
    private String profitMarginEvaluation;
    private String salesPerShareEvaluation;
    private String roeEvaluation;
    private String roeDynamics;
    private String doeEvaluation;
    private String crEvaluation;
    private String qrEvaluation;
    private String priceToCashEvaluation;

    //Анализ растущих компаний из новых развивающихся отраслей
    //Избегать компаний, у которых Р/Е больше скорости роста прибылей за год в процентах
    //Если Р/Е больше 25 даже при большом темпе роста прибылей, продавать при первых признаках падения рынка
    //Больше или меньше справедливой цены
    //Справедливая цена акций
    //incomeSpeedRate, equitablePrice;
    private String peEarnSpeedCompare;
    private String buyOrSellEvaluation;
    private String equitablePrice;

    public AnalysisResult() {
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

    public String getHistoryStability() {
        return historyStability;
    }

    public void setHistoryStability(String historyStability) {
        this.historyStability = historyStability;
    }

    public String getIndustryLessMore() {
        return industryLessMore;
    }

    public void setIndustryLessMore(String industryLessMore) {
        this.industryLessMore = industryLessMore;
    }

    public String getPriceToEPSvalueEvaluation() {
        return priceToEPSvalueEvaluation;
    }

    public void setPriceToEPSvalueEvaluation(String priceToEPSvalueEvaluation) {
        this.priceToEPSvalueEvaluation = priceToEPSvalueEvaluation;
    }

    public String getRevenueDynamics() {
        return revenueDynamics;
    }

    public void setRevenueDynamics(String revenueDynamics) {
        this.revenueDynamics = revenueDynamics;
    }

    public String getEarningRevenueComparing() {
        return earningRevenueComparing;
    }

    public void setEarningRevenueComparing(String earningRevenueComparing) {
        this.earningRevenueComparing = earningRevenueComparing;
    }

    public double getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(double capitalization) {
        this.capitalization = capitalization;
    }

    public String getBookValueEvaluation() {
        return bookValueEvaluation;
    }

    public void setBookValueEvaluation(String bookValueEvaluation) {
        this.bookValueEvaluation = bookValueEvaluation;
    }

    public String getProfitMarginEvaluation() {
        return profitMarginEvaluation;
    }

    public void setProfitMarginEvaluation(String profitMarginEvaluation) {
        this.profitMarginEvaluation = profitMarginEvaluation;
    }

    public String getSalesPerShareEvaluation() {
        return salesPerShareEvaluation;
    }

    public void setSalesPerShareEvaluation(String salesPerShareEvaluation) {
        this.salesPerShareEvaluation = salesPerShareEvaluation;
    }

    public String getRoeEvaluation() {
        return roeEvaluation;
    }

    public void setRoeEvaluation(String roeEvaluation) {
        this.roeEvaluation = roeEvaluation;
    }

    public String getRoeDynamics() {
        return roeDynamics;
    }

    public void setRoeDynamics(String roeDynamics) {
        this.roeDynamics = roeDynamics;
    }

    public String getDoeEvaluation() {
        return doeEvaluation;
    }

    public void setDoeEvaluation(String doeEvaluation) {
        this.doeEvaluation = doeEvaluation;
    }

    public String getCrEvaluation() {
        return crEvaluation;
    }

    public void setCrEvaluation(String crEvaluation) {
        this.crEvaluation = crEvaluation;
    }

    public String getQrEvaluation() {
        return qrEvaluation;
    }

    public void setQrEvaluation(String qrEvaluation) {
        this.qrEvaluation = qrEvaluation;
    }

    public String getPriceToCashEvaluation() {
        return priceToCashEvaluation;
    }

    public void setPriceToCashEvaluation(String priceToCashEvaluation) {
        this.priceToCashEvaluation = priceToCashEvaluation;
    }

    public String getPeEarnSpeedCompare() {
        return peEarnSpeedCompare;
    }

    public void setPeEarnSpeedCompare(String peEarnSpeedCompare) {
        this.peEarnSpeedCompare = peEarnSpeedCompare;
    }

    public String getBuyOrSellEvaluation() {
        return buyOrSellEvaluation;
    }

    public void setBuyOrSellEvaluation(String buyOrSellEvaluation) {
        this.buyOrSellEvaluation = buyOrSellEvaluation;
    }

    public String getEquitablePrice() {
        return equitablePrice;
    }

    public void setEquitablePrice(String equitablePrice) {
        this.equitablePrice = equitablePrice;
    }

    public int getPromise() {
        return promise;
    }

    public void setPromise(int promise) {
        this.promise = this.promise+promise;
    }

    @Override
    public String toString() {
        return "AnalysisResult{" +
                "ticker='" + ticker + '\'' +
                ", year=" + year +
                ", promise=" + promise +
                ", historyStability='" + historyStability + '\'' +
                ", industryLessMore='" + industryLessMore + '\'' +
                ", priceToEPSvalueEvaluation='" + priceToEPSvalueEvaluation + '\'' +
                ", revenueDynamics='" + revenueDynamics + '\'' +
                ", earningRevenueComparing='" + earningRevenueComparing + '\'' +
                ", capitalization=" + capitalization +
                ", bookValueEvaluation='" + bookValueEvaluation + '\'' +
                ", profitMarginEvaluation='" + profitMarginEvaluation + '\'' +
                ", salesPerShareEvaluation='" + salesPerShareEvaluation + '\'' +
                ", roeEvaluation='" + roeEvaluation + '\'' +
                ", roeDynamics='" + roeDynamics + '\'' +
                ", doeEvaluation='" + doeEvaluation + '\'' +
                ", crEvaluation='" + crEvaluation + '\'' +
                ", qrEvaluation='" + qrEvaluation + '\'' +
                ", priceToCashEvaluation='" + priceToCashEvaluation + '\'' +
                ", peEarnSpeedCompare='" + peEarnSpeedCompare + '\'' +
                ", buyOrSellEvaluation='" + buyOrSellEvaluation + '\'' +
                ", equitablePrice='" + equitablePrice + '\'' +
                '}'+"\n";
    }
}
