package model.models;

public class EarningsAnalysisResult {
    //Анализ прибылей
    //1) p/e
    //2) Динамика объема продаж
    //3) Сравнение темпов роста продаж и прибыли
    //4) Меньшая капитализация
    private String ticker;
    private int year;
    private String priceToEPSvalueEvaluation;
    private String revenueDynamics;
    private String earningRevenueComparing;
    private double capitalization;

    //Составляющая суммарной оценки компании
    private int promise;

    public int getPromise() {
        return promise;
    }

    public void setPromise(int promise) {
        this.promise = this.promise + promise;
    }

    public String getPriceToEPSvalueEvaluation() {
        return priceToEPSvalueEvaluation;
    }

    public void setPriceToEPSvalueEvaluation(String priceToEPSvalueEvaluation) {
        this.priceToEPSvalueEvaluation = priceToEPSvalueEvaluation;
    }

    public EarningsAnalysisResult() {
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
}
